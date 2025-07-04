//package org.tk.spring.cache;
//
//import org.ehcache.config.builders.CacheManagerBuilder;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.concurrent.ConcurrentMapCache;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.cache.interceptor.SimpleKeyGenerator;
//import org.springframework.cache.support.SimpleCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
////https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/annotation/EnableCaching.html
//
//@Configuration
//@EnableCaching
//public class CacheJavaConfig extends CachingConfigurerSupport {
//
//    public static final String CACHE_NAME = "default";
//
//    @Override
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager(CACHE_NAME);
//    }
//
//    @Override
//    public KeyGenerator keyGenerator() {
//        return new SimpleKeyGenerator();
//    }
//
//    @Bean
//    public MyCacheableService myService() {
//        return new MyCacheableService();
//    }
//}
