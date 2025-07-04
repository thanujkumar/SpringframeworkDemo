package org.tk.spring.wiring.ex3.auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

//This example uses both xml and annotation  <context:annotation-config/>
public class MainAutoWiring {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext2.xml");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        BeanA beanA = applicationContext.getBean("beanA", BeanA.class);
        System.out.println("--------------Array-----------------------");
        System.out.println(Arrays.toString(beanA.getBeanBs()));
        System.out.println("--------------List-----------------------");
        System.out.println(Arrays.toString(beanA.getBeanBList().toArray()));
        System.out.println("--------------Map-----------------------");
         for (Map.Entry entry : beanA.getBeanBMap().entrySet()) {
             System.out.println(entry.getKey() +" -- "+ entry.getValue());
         }
        System.out.println(beanA.getApplicationContext());
    }
}
