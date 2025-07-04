package org.tk.spring.wiring.ex2.both.xml_annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public class BeanB {

    @Value("#{beanA}")
    private BeanA beanA;

//    @Value("#{beanA}")
//    public void setBeanA( BeanA beanA) {//Using bean Id in SpringEL
//        this.beanA = beanA;
//    }
//
//    public BeanA getBeanA() {
//        return this.beanA;
//    }
}
