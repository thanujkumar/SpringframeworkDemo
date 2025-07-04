package org.tk.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAop {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ConfigureAop.class);
        for (String name : annotationConfigApplicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        ObjectUnderAop aop = annotationConfigApplicationContext.getBean("aop", ObjectUnderAop.class);
        aop.add(3,4);
        aop.doSomeWork();
    }
}
