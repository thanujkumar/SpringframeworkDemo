package org.tk.spring.wiring.ex7;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDefaultWiring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaDefaultWiringConfig.class);

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName); //ComplexUser is also scanned
        }

        applicationContext.close();
    }
}
