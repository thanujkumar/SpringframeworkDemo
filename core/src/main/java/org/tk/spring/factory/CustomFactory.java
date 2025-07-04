package org.tk.spring.factory;

public class CustomFactory {

    public CustomFactory() {
        System.out.println("Instantiating CustomFactory");
    }

    //BeanB created through factory-bean and BeanA through factory-method
    public BeanB getBeanBInstance() {
        System.out.println("BeanB instance called by factory-bean - getBeanBInstance()");
        return new BeanB();
    }
}
