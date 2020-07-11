package com.xujiale.tools.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujiale 2020/7/10 18:57
 */
@EnableCaching
@Configuration
public class CacheConfig {

    public static final String MYSQL_STATUS_57 = "mysql_status_57";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager(MYSQL_STATUS_57);
        concurrentMapCacheManager.setAllowNullValues(false);
        return concurrentMapCacheManager;
    }



}
