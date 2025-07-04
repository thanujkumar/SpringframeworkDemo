package org.tk.spring.proxyfactory.postprocess;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class.getPackage().getName());
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        Cat cat = applicationContext.getBean(Cat.class);
        cat.sound();

        Dog dog = applicationContext.getBean(Dog.class);
        dog.sound();

        applicationContext.close();
    }
}
