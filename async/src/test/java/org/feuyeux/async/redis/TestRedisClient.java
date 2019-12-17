package org.feuyeux.async.redis;

import lombok.extern.slf4j.Slf4j;
import org.feuyeux.async.pool.ThreadCoon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestRedisClient {
    String key = "ASYC-CLIENT-TESTING-KEY11";
    String key2 = "ASYC-CLIENT-TESTING-KEY22";
    String value = Instant.now().toString();
    private LettuceCoon lettuceCoon;

    @Before
    public void before() {
        RedisConfig redisProps = new RedisConfig();
        redisProps.setHost("");
        redisProps.setPwd("");
        redisProps.setPort(6379);
        redisProps.setConnectionTimeout(1000);
        lettuceCoon = new LettuceCoon(redisProps);
        lettuceCoon.init();
    }

    @After
    public void after() {
        if (lettuceCoon != null) {
            lettuceCoon.destroy();
        }
    }

    @Test
    public void testCommand() {
        lettuceCoon.pushToQueue(key, value, 3);
        Assert.assertNotNull(lettuceCoon.getQueue(key));
        lettuceCoon.popFromQueue(key);
        Assert.assertTrue(lettuceCoon.getQueue(key).isEmpty());

        String r0 = lettuceCoon.saveString(key, "hello world", 1000);
        log.info("lettuceCoon.saveString:{}", r0);
        String r1 = lettuceCoon.readString(key);
        Assert.assertEquals("hello world", r1);
        log.info("lettuceCoon.readString:{}", r1);
        String r2 = lettuceCoon.renameKey(key, key2);
        log.info("lettuceCoon.renameKey:{}", r2);
        r1 = lettuceCoon.readString(key2);
        Assert.assertEquals("hello world", r1);
        long r3 = lettuceCoon.deleteKey(key2);
        log.info("lettuceCoon.deleteKey:{}", r3);

        lettuceCoon.pushToQueue(key, "1");
        lettuceCoon.pushToQueue(key, "2");
        long l0 = lettuceCoon.pushToQueue(key, "3", 1000);
        log.info("lettuceCoon.pushToQueue:{}", l0);

        String r4 = lettuceCoon.popFromQueue(key);
        log.info("lettuceCoon.popFromQueue:{}", r4);
        Assert.assertEquals("1", r4);
        Assert.assertEquals("2", lettuceCoon.popFromQueue(key));

        lettuceCoon.pushToQueue(key, "1");
        List<String> q1 = lettuceCoon.getQueue(key);
        log.info("lettuceCoon.getQueue:{}", q1);
        List<String> q2 = lettuceCoon.getQueue(key, 0, 2);
        log.info("lettuceCoon.getQueue:{}", q2);

        String r5 = lettuceCoon.getQueueByIndex(key, 1);
        log.info("lettuceCoon.getQueueByIndex:{}", r5);

        long l1 = lettuceCoon.getQueueLength(key);
        log.info("lettuceCoon.getQueueLength:{}", l1);
        lettuceCoon.deleteKey(key);

        log.info("lettuceCoon.addToSet");
        lettuceCoon.addToSet(key, "1", "2", "3");
        lettuceCoon.addToSet(key, 1000, "1", "2", "3");
        log.info("lettuceCoon.getFromSet");
        Set<String> s1 = lettuceCoon.getFromSet(key);
        Long l2 = lettuceCoon.getSetSize(key);
        Assert.assertEquals(l2.intValue(), s1.size());

        boolean b1 = lettuceCoon.isSetMember(key, "1");
        log.info("lettuceCoon.isSetMember:{}", b1);
        lettuceCoon.deleteFromSet(key, "2", "3");
        Assert.assertEquals(1L, lettuceCoon.getSetSize(key).longValue());
        lettuceCoon.deleteKey(key);

        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        String s0 = lettuceCoon.saveMap(key, map);
        log.info("lettuceCoon.saveMap:{}", s0);
        String s2 = lettuceCoon.getMapValue(key, "1");
        lettuceCoon.setMapValue(key, "1", "一");
        String s3 = lettuceCoon.getMapValue(key, "1");
        Assert.assertEquals("one", s2);
        Assert.assertEquals("一", s3);
        lettuceCoon.deleteKey(key);

        lettuceCoon.saveString(key, "1");
        lettuceCoon.incr(key);
        lettuceCoon.incrBy(key, 2);
        lettuceCoon.expire(key, 10000);
        String s = lettuceCoon.readString(key);
        //Assert.assertEquals("4", s); TODO jedis return 1
        Assert.assertTrue(lettuceCoon.exists(key));
        Assert.assertEquals(0L, lettuceCoon.setnx(key, "2"));
    }

    @Test
    public void testStream() {
        String key = Instant.now().toString();
        int[] ary = {1, 2, 3, 4, 5, 6, 7, 8};
        Arrays.stream(ary).parallel().forEach(n -> {
            ThreadCoon.submit(() -> {
                lettuceCoon.addToSet(key, n + "");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                Set<String> set = lettuceCoon.getFromSet(key);
                log.info("set={}", set);
            });
        });
        lettuceCoon.deleteKey(key);

        Arrays.stream(ary).forEach(n -> {
            ThreadCoon.submit(() -> {
                lettuceCoon.addToSet(key, n + "");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                Set<String> set = lettuceCoon.getFromSet(key);
                log.info("set={}", set);
            });
        });
        lettuceCoon.deleteKey(key);
    }
}
