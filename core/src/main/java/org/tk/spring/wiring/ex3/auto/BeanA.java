package org.tk.spring.wiring.ex3.auto;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

@Data
public class BeanA {
    //This @Autowired annotation is highly flexible and powerful, and definitely better than “autowire” (no,byType,constructor,default,byName) attribute in bean configuration file.
    //@Qualifier is used if same type of two beans exists
    //applicationContext2.xml has 3 BeanB instances configured with different bean name;
    @Autowired // is by type
    private BeanB[] beanBs;

    @Autowired
    private List<BeanB> beanBList;

    @Autowired
    private Map<String, BeanB> beanBMap;

    @Autowired //Automatically injected by container
    ApplicationContext applicationContext;
}
