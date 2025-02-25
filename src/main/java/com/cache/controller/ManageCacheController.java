package com.cache.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ManageCacheController.PATH)
@RequiredArgsConstructor
public class ManageCacheController {

    public static final String PATH = "/api/v1/cache";

}
