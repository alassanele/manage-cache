package com.cache.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CacheServiceTest {

    @Test
    public void testCachePutAndGet() {
        CacheService cacheService = new CacheService();
        cacheService.put("testKey", "testValue", 5);
        assertEquals("testValue", cacheService.get("testKey"));
    }

    @Test
    public void testCacheExpiration() throws InterruptedException {
        CacheService cacheService = new CacheService();
        cacheService.put("testKey", "testValue", 1);
        Thread.sleep(2000);
        assertNull(cacheService.get("testKey"));
    }

}
