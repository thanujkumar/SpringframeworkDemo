package org.tk.spring.proxyfactory.springbased;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Joinpoint;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Arrays;

public class SpringProxy {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new MyAdvice());
        factory.setExposeProxy(true);

        Pojo proxy = (Pojo) factory.getProxy();
        int result = proxy.add(3,4);
        System.out.println("Result from proxy : "+ result);
        proxy = (Pojo) factory.getProxy();
        System.out.println("Result from proxy : "+ proxy.add(5,6));
    }
}

class SimplePojo implements  Pojo {

    public SimplePojo() {
        System.out.println("SimplePojo ="+this);
    }
    public int add(int a, int b) {
        return a + b;
    }
}

interface  Pojo {
    int add(int a, int b);
}

class MyAdvice implements Advice, Advisor, MethodInterceptor {

    public MyAdvice() {
        System.out.println("MyAdvice ="+this);
    }

    @Override
    public Advice getAdvice() {
        return this;
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Do some proxy work on method = "+ invocation.getMethod());
        System.out.println("Arguments passed for method = "+ Arrays.toString(invocation.getArguments()));
        return invocation.proceed();
    }
}