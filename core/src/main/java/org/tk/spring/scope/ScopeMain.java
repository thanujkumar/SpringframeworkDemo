package org.tk.spring.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ScopeMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =   new AnnotationConfigApplicationContext(ScopeMain.class.getPackage().getName());
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        context.close();
    }
}
