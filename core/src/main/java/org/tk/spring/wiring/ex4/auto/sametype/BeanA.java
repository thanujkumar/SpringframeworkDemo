package org.tk.spring.wiring.ex4.auto.sametype;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

@Data
public class BeanA {

    //Automatically injected by container
    @Autowired
    ApplicationContext applicationContext;

    //applicationContext3.xml has 3 BeanB instances configured with different bean name; which one to inject?
    @Autowired//by type so error unless qualifier is provided
    @Qualifier("beanB1")
    private BeanB beanB;

    @Autowired(required = false)//If no matching bean is found will ignore instead of throwing exception as required is false "optional"
    private Integer beanC; //this will be null
}
