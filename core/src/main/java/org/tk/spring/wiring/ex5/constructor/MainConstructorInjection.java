package org.tk.spring.wiring.ex5.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class MainConstructorInjection {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext4.xml");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);//Will not see BeanB listed

    }
}
