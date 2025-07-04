package org.tk.spring.wiring.ex1.javabased;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//Look at unit tests
public class MainWiring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaWiringConfig.class);

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

       applicationContext.close();
    }
}
