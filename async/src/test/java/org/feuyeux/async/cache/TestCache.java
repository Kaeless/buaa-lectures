package org.feuyeux.async.cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestCache {

    @Test
    public void test() throws InterruptedException {
        CacheCoon cacheCoon = new CacheCoon(CacheConfig.builder()
                .maxSize(2)
                .expireAfterAccessSnd(2)
                .expireAfterWriteSnd(1)
                .build());
        cacheCoon.put("X", "1");
        Assert.assertEquals("1", cacheCoon.get("X"));
        TimeUnit.SECONDS.sleep(2);
        Assert.assertNull(cacheCoon.get("X"));

        cacheCoon.put("X", "1");
        cacheCoon.put("Y", "1");
        cacheCoon.put("Z", "1");

        Assert.assertNull(cacheCoon.get("X"));
        Assert.assertEquals("1", cacheCoon.get("Y"));
        Assert.assertEquals("1", cacheCoon.get("Z"));

        cacheCoon.put("X", "1");
        TimeUnit.SECONDS.sleep(1);
        Assert.assertNull(cacheCoon.get("X"));
    }
}
