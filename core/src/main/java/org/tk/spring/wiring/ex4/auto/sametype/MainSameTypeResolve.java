package org.tk.spring.wiring.ex4.auto.sametype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.Arrays;

//Example of how to use @Autowired and @Qualifier with xml configuration
public class MainSameTypeResolve {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext3.xml");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        BeanA beanA = applicationContext.getBean("beanA", BeanA.class);
        BeanB beanB = beanA.getBeanB();
        System.out.println(beanB);

        System.out.println(beanB.getBeanA()); //set by @Value("#{beanA}") SpEL

        System.out.println(beanA.getBeanC()); //null as there is no bean of  type Integer in the container and required=false

    }
}
