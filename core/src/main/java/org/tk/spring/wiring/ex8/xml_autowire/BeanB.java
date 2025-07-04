package org.tk.spring.wiring.ex8.xml_autowire;

public class BeanB {

    private BeanA beanA;
//    private BeanC beanC;
//   <bean id="beanB" class="org.tk.spring.xml_autowire.BeanB" autowire="constructor" />
//    public BeanB(BeanC beanC) {
//        System.out.println("BeanB-" + this);
//        System.out.println("Injecteed BeanC to BeanB constructor-" + beanC);
//    }

    public BeanB() {
        System.out.println("Created BeanB-" + this);
    }

    public BeanA getBeanA() {
        return beanA;
    }

    public void setBeanA(BeanA beanA) {
        System.out.println("Injecting beanA - " + beanA);
        this.beanA = beanA;
    }
}
