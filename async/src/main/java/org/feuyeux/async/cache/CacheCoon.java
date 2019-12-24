package org.feuyeux.async.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheCoon {
    private Cache<String, String> cache;

    public CacheCoon(CacheConfig cacheConfig) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(cacheConfig.getMaxSize())
                .expireAfterAccess(cacheConfig.getExpireAfterAccessSnd(), TimeUnit.SECONDS)
                .expireAfterWrite(cacheConfig.getExpireAfterWriteSnd(), TimeUnit.SECONDS)
                .build();
    }

    public String get(String key) {
        return cache.getIfPresent(key);
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }
}
