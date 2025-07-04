package org.tk.spring.mix_singleton_prototype.option1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//In singleton there is prototype object which should return new object for every call
public class Singleton implements ApplicationContextAware {

    private Prototype prototype;
    private ApplicationContext applicationContext;


    //Each call get new instance as bean is defined as prototype in xml configuration
    public Prototype createPrototype() {
        System.out.println("createPrototype called, using applicationContext to lookup that bean and return");
       return applicationContext.getBean("prototype", Prototype.class);
    }

    //Though bean is prototype, each call for this will return same object
    public Prototype getPrototype() {
        return prototype; // You can change createPrototype method as getPrototype, here these two methods shows the difference
        // - getPrototype will always return same object whereas createPrototype creates new object each time
    }

    public void setPrototype(Prototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
