package org.tk.spring.wiring.ex2.both.xml_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class BeanA {

    private BeanB beanB;
    private int totalCores;

    @Autowired
    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }

    public BeanB getBeanB() {
        return this.beanB;
    }

    @Value("#{T(Runtime).getRuntime().availableProcessors()}") //Using SpringEL for static method
    public void setTotalCores(int totalCores) {
        this.totalCores = totalCores;
    }

    public int getTotalCores() {
        return totalCores;
    }


}
