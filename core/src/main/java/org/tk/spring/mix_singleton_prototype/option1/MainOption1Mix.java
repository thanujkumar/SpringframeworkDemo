package org.tk.spring.mix_singleton_prototype.option1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

//In this Option we use ApplicationContextAware on Singleton so that we lookup the prototype from applicationContext each time, as xml defines
// Prototype as scope=prototype, each time applicationContext returns new one.
public class MainOption1Mix {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext7.xml");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        Singleton singleton = applicationContext.getBean("singleton", Singleton.class);

        Prototype prototype1 =  singleton.createPrototype(); // returns new object
        System.out.println(prototype1);
        Prototype prototype2 =  singleton.createPrototype(); // returns new object
        System.out.println(prototype2);

        Prototype prototype3 =  singleton.getPrototype(); // will return same object
        System.out.println(prototype3);
        Prototype prototype4 =  singleton.getPrototype(); // will return same object
        System.out.println(prototype4);


    }
}
