package com.cache.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheServiceTest {

    private CacheService cacheService;

    @BeforeEach
    public void setUp() {
        cacheService = new CacheService();
    }

    @Test
    public void should_put_and_get_cache() {
        cacheService.put("testKey", "testValue", 5);
        Assertions.assertEquals("testValue", cacheService.get("testKey"));
    }

    @Test
    public void test_cache_expiration() throws InterruptedException {
        cacheService.put("testKey", "testValue", 1);
        Thread.sleep(2000);
        Assertions.assertNull(cacheService.get("testKey"));
    }

    @Test
    public void should_get_with_existing_key() {
        cacheService.put("key1", "value1", 5);
        Assertions.assertEquals("value1", cacheService.get("key1"));
    }

    @Test
    public void should_get_with_non_existing_Key() {
        Assertions.assertNull(cacheService.get("nonExistingKey"));
    }

    @Test
    public void should_get_with_expired_key() throws InterruptedException {
        cacheService.put("key2", "value2", 1);
        Thread.sleep(2000);
        Assertions.assertNull(cacheService.get("key2"));
    }

    @Test
    public void test_evict() {
        cacheService.put("key3", "value3", 5);
        cacheService.evict("key3");
        Assertions.assertNull(cacheService.get("key3"));
    }

    @Test
    public void test_clear() {
        cacheService.put("key4", "value4", 5);
        cacheService.put("key5", "value5", 5);
        cacheService.clear();
        Assertions.assertNull(cacheService.get("key4"));
        Assertions.assertNull(cacheService.get("key5"));
    }

}
