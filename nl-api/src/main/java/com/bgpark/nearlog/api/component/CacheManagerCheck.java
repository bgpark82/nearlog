package com.bgpark.nearlog.api.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheManagerCheck implements CommandLineRunner {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void run(String... args) throws Exception {
        log.info("Using cache manager: {}", cacheManager.getClass().getName());
    }
}
