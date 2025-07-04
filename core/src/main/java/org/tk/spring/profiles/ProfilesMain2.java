package org.tk.spring.profiles;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
//https://www.baeldung.com/spring-profiles

public class ProfilesMain2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().addActiveProfile("embedded");
        applicationContext.scan(ProfilesMain2.class.getPackage().getName());
        applicationContext.refresh();

        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        RuntimeWhichDS runtimeWhichDS = applicationContext.getBean(RuntimeWhichDS.class);
        runtimeWhichDS.printProfileDS();
    }
}
