//package org.tk.spring.cache;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.tk.spring.ehcache.MyEhCacheableService;
//
//import java.util.Arrays;
//
//public class CacheMain {
//
//    public static void main(String[] args) throws InterruptedException {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CacheMain.class.getPackage().getName());
//        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
//
//        MyCacheableService service = applicationContext.getBean(MyCacheableService.class);
//
//        service.square(15l); // will cache it, you would see MyCacheableService.square console output
//        Thread.sleep(1000); //sleep for 10 sec
//        service.square(15l);//Request same and no printing on console as it is fetched from cache
//
//        service.square(25l);
//
//        //Now let us call evict for number 15
//        service.deleteCache(15l);
//        service.square(15l); //This will call method once again and cache
//
//        //Now remove all entries
//        service.removeAllFromCache();
//        service.square(15l);//will be cached when call is made
//        service.square(15l);//from cache
//        service.square(25l);//will be cached when call is made
//
//        applicationContext.close();
//    }
//
//}
