package org.tk.spring.aop;


public class ObjectUnderAop {
    public void doSomeWork() {
        System.out.println("ObjectUnderAop.doSomeWork");
    }

    public int add(int a, int b) {
        System.out.println("ObjectUnderAop.add(int a, int b)");
        return a + b;
    }
}
