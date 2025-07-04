package org.tk.spring.wiring.ex2.both.xml_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

//@Autowired - inject by type
//@Resource - inject by name
public class MainBothAnnotationAndXml {

    //having <context:annotation-config /> spring adds many internal Processor which process annotation that are configured in xml
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext1.xml");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        //If Id is not given then bean for BeanB class
        BeanB beanB = applicationContext.getBean("org.tk.spring.wiring.ex2.both.xml_annotation.BeanB#0", BeanB.class);
        System.out.println(beanB);//singleton

        //Here @Autowired annotation is set which will be processed and BeanA is set
        BeanA beanA = applicationContext.getBean("beanA", BeanA.class);

        System.out.println(beanA.getBeanB());//singleton

        //Using SpringEL
        System.out.println(beanA.getTotalCores());

  /*
       https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/expressions.html

       Using Spring Expression Language, we can:

        Refer to other beans by id attribute
        Refer to the properties and invoke methods defined in other beans
        Refer to the static constants and invoke static methods
        Perform Mathematical operations on values
        Perform Relational and Logical comparisons
        Perform Regular Expression Matching

        */

        System.out.println(beanB.getBeanA());
    }
}
