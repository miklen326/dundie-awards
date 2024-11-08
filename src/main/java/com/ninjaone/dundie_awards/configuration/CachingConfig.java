package com.ninjaone.dundie_awards.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager() {
        /*
         * To ensure that award updates are consistently reflected in both the database and cached total amounts,
         * we use TransactionAwareCacheManagerProxy. This class provides a transaction-aware caching mechanism
         * within Spring. By using TransactionAwareCacheManagerProxy, cache updates occur only after the transaction
         * is successfully committed, preventing any discrepancies between the cache and
         * the database due to incomplete or failed transactions.
         */
        return new TransactionAwareCacheManagerProxy(new ConcurrentMapCacheManager("totalAwards"));
    }
}
