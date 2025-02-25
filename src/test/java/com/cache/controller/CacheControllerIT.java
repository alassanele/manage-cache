package com.cache.controller;

import com.cache.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CacheControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_put_value_in_cache() throws Exception {
        String key = "testKey";
        String value = "testValue";
        long ttl = 60;
        mockMvc.perform(post("/api/cache/{key}", key)
                    .param("ttl", String.valueOf(ttl))
                    .content("\"" + value + "\"")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        mockMvc.perform(get("/api/cache/testKey"))
                .andExpect(status().isOk())
                .andExpect(content().string("testValue"));
    }

    @Test
    public void test_evict() throws Exception {
        String key = "testKey";

        CacheService cacheService = new CacheService();
        cacheService.put(key, "testValue", 60);

        mockMvc.perform(delete("/api/cache/{key}", key))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/cache/{key}", key))
                .andExpect(status().isNotFound());
    }

}
