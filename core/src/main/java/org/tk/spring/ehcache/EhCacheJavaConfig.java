//package org.tk.spring.ehcache;
//
//
//import org.ehcache.jsr107.EhcacheCachingProvider;
//import org.ehcache.xml.XmlConfiguration;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.jcache.JCacheCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.cache.CacheManager;
//import javax.cache.Caching;
//import java.net.URL;
//
////https://www.baeldung.com/spring-boot-ehcache
//
//@Configuration
//@EnableCaching
//public class EhCacheJavaConfig {
//
//    @Bean
//    public JCacheCacheManager jCacheCacheManager() {
//        JCacheCacheManager jCacheManager = new JCacheCacheManager(cacheManager());
//        return jCacheManager;
//    }
//
//    //Programmatic - https://www.ehcache.org/documentation/3.3/getting-started.html
//    //JCache Programmetic - https://www.ehcache.org/documentation/3.3/107.html
//    @Bean(destroyMethod = "close")
//    public CacheManager cacheManager() {
//        URL url = Thread.currentThread().getContextClassLoader().getResource("ehcache.xml");
//        org.ehcache.config.Configuration xmlConfig = new XmlConfiguration(url);
//        EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
//        //squareCache is alias as defined in ehcache.xml
//        return provider.getCacheManager(provider.getDefaultURI(), xmlConfig);
//    }
//}
