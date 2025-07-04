package org.tk.spring.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Calendar;

public class MainCustomFactory {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext6.xml");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        BeanA beanA = applicationContext.getBean("beanA", BeanA.class);
        System.out.println(beanA.getCalendar().get(Calendar.YEAR));

        //Bean got from CustomFactory
        BeanB  beanB = applicationContext.getBean("beanB", BeanB.class);
        System.out.println(beanB);

    }
}
