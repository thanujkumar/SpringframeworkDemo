package org.tk.spring.spel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Manipulate Object Graph.
 * Evaluate at Runtime.
 * Configuration.
 */
public class SpELMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpELMain.class.getPackage().getName());
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
