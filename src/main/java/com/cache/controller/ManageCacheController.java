package com.cache.controller;

import com.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(ManageCacheController.PATH)
@RequiredArgsConstructor
public class ManageCacheController {

    public static final String PATH = "/api/cache";

    private final CacheService cacheService;

    @PostMapping("/{key}")
    public ResponseEntity<Void> put(@PathVariable String key, @RequestBody Object value, @RequestParam long ttl) {
        cacheService.put(key, value, ttl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{key}")
    public ResponseEntity<Object> get(@PathVariable String key) {
        Object value = cacheService.get(key);
        if (value != null) {
            return ResponseEntity.ok(value);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> evict(@PathVariable String key) {
        cacheService.evict(key);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clear() {
        cacheService.clear();
        return ResponseEntity.ok().build();
    }

}
