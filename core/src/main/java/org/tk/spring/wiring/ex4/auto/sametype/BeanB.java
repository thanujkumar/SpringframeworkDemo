package org.tk.spring.wiring.ex4.auto.sametype;

import org.springframework.beans.factory.annotation.Value;


public class BeanB {

    //@Value("#{beanA}")
    private BeanA beanA;

    @Value("#{beanA}") //@Value can be set to variable as above
    public void setBeanA( BeanA beanA) {//Using bean Id in SpringEL
        this.beanA = beanA;
    }

    public BeanA getBeanA() {
        return this.beanA;
    }
}
