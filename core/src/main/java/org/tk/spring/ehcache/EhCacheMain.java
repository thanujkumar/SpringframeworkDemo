//package org.tk.spring.ehcache;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.Arrays;
//
//public class EhCacheMain {
//
//    public static void main(String[] args) throws InterruptedException {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(EhCacheMain.class.getPackage().getName());
//        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
//
//        MyEhCacheableService service = applicationContext.getBean(MyEhCacheableService.class);
//        for (int i = 1; i <= 100; i++) {
//            System.out.println(i + " = Square of 15 = " + service.square(15l)); // will cache it, you would see MyCacheableService.square console output
//            Thread.sleep(1000); //sleep for 1 sec
//            if (i % 20 == 0) {
//                service.evitCacheKey(15l);
//            }
//        }
//
//        System.out.println("Square of 15 = " + service.square(15l));//Request same and no printing on console as it is fetched from cache
//
//        System.out.println("Square of 12 = " + service.square(12l));
//        System.out.println("Square of 13 = " + service.square(13l));
//        System.out.println("Square of 14 = " + service.square(14l));
//        System.out.println("Square of 12 = " + service.square(12l));
//
//        System.out.println("====================CALL WITH-IN, DOES PROXY WORKS=======================");
//        System.out.println("Square of 12 (cache will not work) = " + service.getSquare(12l));
//        System.out.println("Square of 12 (cache will not work) = " + service.getSquare(12l));
//
//        System.out.println("====================SLEEPING 60s, So 30s TTL works in next call=======================");
//        Thread.sleep(60000);//sleep for one minute to get eviction event
//
//
//        System.out.println("EXPIRED event should be trigger (above) " + service.square(15l));
//
//        applicationContext.close();
//    }
//
//}
