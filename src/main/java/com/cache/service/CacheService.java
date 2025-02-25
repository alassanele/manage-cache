package com.cache.service;

import com.cache.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private final ConcurrentHashMap<String, CacheUtils> cache = new ConcurrentHashMap<>();

    public void put(String key, Object value, long ttlInSeconds) {
        cache.put(key, new CacheUtils(value, ttlInSeconds));
    }

    public Object get(String key) {
        CacheUtils entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key);
            return null;
        }
        return entry.getValue();
    }

    public void evict(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
}

