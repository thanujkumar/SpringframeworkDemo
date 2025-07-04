//package org.tk.spring.cache;
//
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//
//import java.math.BigDecimal;
//
//public class MyCacheableService {
//
//    @Cacheable(value = CacheJavaConfig.CACHE_NAME, key = "#number", condition = "#number > 10")
//    public BigDecimal square(Long number) {
//        BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
//        System.out.println("MyCacheableService.square called with " + number + " and result is " + square);
//        return square;
//    }
//
//    //Manually you evict the cache by calling this method
//    @CacheEvict(CacheJavaConfig.CACHE_NAME)
//    public void deleteCache(Long number) {
//        System.out.println("MyCacheableService.deleteCache " + number);
//    }
//
//    @CacheEvict(value = CacheJavaConfig.CACHE_NAME, allEntries = true)
//    public void removeAllFromCache() {
//        System.out.println("MyCacheableService.removeAllFromCache ");
//    }
//}
