//package org.tk.spring.ehcache;
//
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//@Service
//public class MyEhCacheableService {
//
//    //As it is internal call to square will proxy work?
//    public String getSquare(Long number) {
//        return "" + square(number);
//    }
//
//    @Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
//    public BigDecimal square(Long number) {
//        BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
//        System.out.println("MyCacheableService.square called with result " + square);
//        return square;
//    }
//
//    @CacheEvict("squareCache")
//    public void evitCacheKey(Long key) {
//        System.out.println("MyEhCacheableService.evitCacheKey called for key " + key);
//    }
//}
