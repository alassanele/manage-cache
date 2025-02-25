package com.cache.utils;

import lombok.Getter;

import java.time.Instant;

@Getter
public class CacheUtils {

    private final Object value;
    private final Instant expiryTime;

    public CacheUtils(Object value, long ttlInSeconds) {
        this.value = value;
        this.expiryTime = Instant.now().plusSeconds(ttlInSeconds);
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiryTime);
    }
}