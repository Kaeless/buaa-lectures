package org.feuyeux.async.redis;

import com.google.common.base.Strings;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author feuyeux
 */
@Slf4j
public class LettuceCoon {
    private RedisConfig redisConf;
    private io.lettuce.core.RedisClient client;
    private StatefulRedisConnection<String, String> connection;

    @Setter
    @Getter
    private int dbIndex = 1;

    public LettuceCoon(RedisConfig redisConf) {
        this.redisConf = redisConf;
    }

    public void init() {
        RedisURI redisUri = RedisURI.Builder.redis(redisConf.getHost())
                .withPort(redisConf.getPort())
                .withTimeout(Duration.ofMillis(redisConf.getConnectionTimeout()))
                .withPassword(redisConf.getPwd())
                .withDatabase(dbIndex)
                .build();
        client = io.lettuce.core.RedisClient.create(redisUri);
        connection = client.connect();
    }

    public void destroy() {
        if (connection != null) {
            connection.close();
        }
        if (client != null) {
            client.shutdown();
        }
    }

    public String flushDB() {
        log.warn("flushDB");
        final RedisFuture<String> future = getCommands().flushdb();
        return futureGet(future);
    }

    /***** string *****/
    public String saveString(String key, String value) {
        if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(value)) {
            return null;
        }
        return saveString(key, value, 0);
    }

    public String saveString(String key, String value, int seconds) {
        if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(value)) {
            return null;
        }
        final RedisFuture<String> f;
        if (seconds > 0) {
            f = getCommands().setex(key, seconds, value);

        } else {
            f = getCommands().set(key, value);
        }
        return futureGet(f);
    }

    public String readString(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        final RedisFuture<String> f = getCommands().get(key);
        return futureGet(f);
    }

    /**
     * queue
     * lpop <-- rpush
     * lrange [old <-- new]
     **/
    public long pushToQueue(String key, String value) {
        if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(value)) {
            return -1L;
        }
        return pushToQueue(key, value, 0);
    }

    public long pushToQueue(String key, String value, int seconds) {
        if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(value)) {
            return -1L;
        }
        final RedisFuture<Long> f = getCommands().rpush(key, value);
        return saveExpire(key, seconds, f);
    }

    public String popFromQueue(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        final RedisFuture<String> f = getCommands().lpop(key);
        return futureGet(f);
    }

    public List<String> getQueue(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        final RedisFuture<List<String>> f = getCommands().lrange(key, 0, -1);
        return futureGet(f);
    }

    public List<String> getQueue(String key, long start, long end) {
        if (Strings.isNullOrEmpty(key) || start < 0 || end < 0 || start > end) {
            return null;
        }
        final RedisFuture<List<String>> f = getCommands().lrange(key, start, end);
        return futureGet(f);
    }

    public String getQueueByIndex(String key, long index) {
        if (Strings.isNullOrEmpty(key) || index < 0) {
            return null;
        }
        final RedisFuture<String> f = getCommands().lindex(key, index);
        return futureGet(f);
    }

    public long getQueueLength(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return -1;
        }
        final RedisFuture<Long> f = getCommands().llen(key);
        Long l = futureGet(f);
        return l == null ? -1L : l;
    }

    public String getFirstOne(String key) {
        return getQueueByIndex(key, 0);
    }

    public String getLastOne(String key) {
        return getQueueByIndex(key, getQueueLength(key) - 1);
    }

    /***** set *****/
    public long addToSet(String key, String... value) {
        return addToSet(key, 0, value);
    }

    public long addToSet(String key, int seconds, String... value) {
        if (Strings.isNullOrEmpty(key) || value == null) {
            return -1;
        }
        final RedisFuture<Long> f = getCommands().sadd(key, value);
        return saveExpire(key, seconds, f);
    }

    public Set<String> getFromSet(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        final RedisFuture<Set<String>> f = getCommands().smembers(key);
        return futureGet(f);
    }

    public Long getSetSize(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        final RedisFuture<Long> f = getCommands().scard(key);
        return futureGet(f);
    }

    public boolean isSetMember(String key, String value) {
        if (Strings.isNullOrEmpty(key)) {
            return false;
        }
        final RedisFuture<Boolean> f = getCommands().sismember(key, value);

        Boolean b = futureGet(f);
        return b == null ? false : b;
    }

    public long deleteFromSet(String key, String... value) {
        if (Strings.isNullOrEmpty(key) || value == null) {
            return -1;
        }
        final RedisFuture<Long> f = getCommands().srem(key, value);
        Long l = futureGet(f);
        return l == null ? -1L : l;
    }

    /***** map *****/
    public String saveMap(String key, Map<String, String> value) {
        if (Strings.isNullOrEmpty(key) || value == null || value.isEmpty()) {
            return null;
        }
        return saveMap(key, value, 0);
    }

    public String saveMap(String key, Map<String, String> value, int seconds) {
        if (Strings.isNullOrEmpty(key) || value == null || value.isEmpty()) {
            return null;
        }

        final RedisFuture<String> f = getCommands().hmset(key, value);
        if (seconds > 0) {
            String ret = futureGet(f);
            if (!Strings.isNullOrEmpty(ret)) {
                getCommands().expire(key, seconds);
            }
            return ret;
        } else {
            return futureGet(f);
        }
    }

    public long setMapValue(String key, String field, String value) {
        if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(field) || Strings.isNullOrEmpty(value)) {
            return -1;
        }
        final RedisFuture<Boolean> f = getCommands().hset(key, field, value);
        Boolean b = futureGet(f);
        return b == null ? -1L : b ? 1L : 0;
    }

    public String getMapValue(String key, String field) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        final RedisFuture<String> f = getCommands().hget(key, field);
        return futureGet(f);
    }

    public Map<String, String> getMap(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        final RedisFuture<Map<String, String>> f = getCommands().hgetall(key);
        return futureGet(f);
    }


    /***** common *****/

    public long deleteKey(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return -1;
        }
        final RedisFuture<Long> f = getCommands().del(key);
        Long l = futureGet(f);
        return l == null ? -1L : l;
    }

    @Deprecated
    public long deleteString(String key) {
        return deleteKey(key);
    }

    public String renameKey(String oldKey, String newKey) {
        if (Strings.isNullOrEmpty(oldKey) || Strings.isNullOrEmpty(newKey)) {
            return null;
        }
        final RedisFuture<String> f = getCommands().rename(oldKey, newKey);
        return futureGet(f);
    }

    public long incr(String key) {
        final RedisFuture<Long> f = getCommands().incr(key);
        Long l = futureGet(f);
        return l == null ? -1L : l;
    }

    public long incrBy(String key, int addValue) {
        if (Strings.isNullOrEmpty(key)) {
            return -1;
        }
        final RedisFuture<Long> f = getCommands().incrby(key, addValue);
        Long l = futureGet(f);
        return l == null ? -1L : l;
    }

    public long decr(String key) {
        final RedisFuture<Long> f = getCommands().decr(key);
        Long l = futureGet(f);
        return l == null ? -1L : l;
    }

    public long decrBy(String key, int addValue) {
        if (Strings.isNullOrEmpty(key)) {
            return -1;
        }
        final RedisFuture<Long> f = getCommands().decrby(key, addValue);
        Long l = futureGet(f);
        return l == null ? -1L : l;
    }

    /**
     * @param key key
     * @return 若 key 存在，返回 1 ，否则返回 0 。
     */

    public boolean exists(String key) {
        final RedisFuture<Long> f = getCommands().exists(key);
        Long l = futureGet(f);
        return l != null && l == 1;
    }

    public long setnx(String key, String value) {
        if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(value)) {
            return -1;
        }
        final RedisFuture<Boolean> f = getCommands().setnx(key, value);
        Boolean b = futureGet(f);
        return b == null ? -1L : b ? 1L : 0;
    }

    public long expire(String key, int seconds) {
        if (Strings.isNullOrEmpty(key)) {
            return -1;
        }
        final RedisFuture<Boolean> f = getCommands().expire(key, seconds);
        Boolean b = futureGet(f);
        return b == null ? -1L : b ? 1L : 0;
    }

    public <T> T eval(String script, ScriptOutputType outputType, String[] keys, String[] args) {
        RedisAsyncCommands<String, String> commands = getCommands();
        RedisFuture<T> result = commands.eval(script, outputType, keys, args);
        return futureGet(result);
    }

    private RedisAsyncCommands<String, String> getCommands() {
        return getCommands(false);
    }

    private RedisAsyncCommands<String, String> getCommands(boolean multi) {
        RedisAsyncCommands<String, String> commands = connection.async();
        //commands.select(dbIndex);
        if (multi) {
            commands.multi();
        }
        return commands;
    }

    private Object exec(RedisAsyncCommands<String, String> commands) {
        final RedisFuture<TransactionResult> f = commands.exec();
        TransactionResult transactionResult = futureGet(f);
        if (transactionResult == null || transactionResult.isEmpty()) {
            return null;
        }
        return transactionResult.get(transactionResult.size() - 1);
    }

    private <T> T futureGet(RedisFuture<T> future) {
        try {
            return future.get(redisConf.getAsyncGetTimeout(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("Lettuce Error", e);
            return null;
        }
    }

    private long saveExpire(String key, int seconds, RedisFuture<Long> f) {
        Long l = futureGet(f);
        if (seconds > 0) {
            long length = l == null ? -1L : l;
            if (length > 0) {
                getCommands().expire(key, seconds);
            }
            return length;
        } else {
            return l == null ? -1L : l;
        }
    }
}
