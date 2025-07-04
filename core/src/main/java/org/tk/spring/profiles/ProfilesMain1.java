package org.tk.spring.profiles;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
//https://www.baeldung.com/spring-profiles

public class ProfilesMain1 {
     static {
         System.setProperty("spring.profiles.active", "production");
     }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProfilesMain1.class.getPackage().getName());
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        RuntimeWhichDS runtimeWhichDS = applicationContext.getBean(RuntimeWhichDS.class);
        runtimeWhichDS.printProfileDS();
    }
}
