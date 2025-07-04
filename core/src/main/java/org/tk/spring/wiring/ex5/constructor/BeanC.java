package org.tk.spring.wiring.ex5.constructor;

public class BeanC {

    private String name;

    public BeanC(BeanA myBean, BeanB beanB) {
        System.out.println(getClass());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName : "+ name);
        this.name = name;
    }


}
