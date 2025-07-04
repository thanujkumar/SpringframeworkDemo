package org.tk.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor, Ordered {

    //AutowiredAnnotationBeanPostProcessor //see as example

    public MyBeanPostProcessor() {
        System.out.println("===MyBeanPostProcessor constructor called===============");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("14: BeanPostProcessor.postProcessAfterInitialization :=[" + beanName + "] = " + bean);
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("11: BeanPostProcessor.postProcessBeforeInitialization :=[" + beanName + "] = " + bean);
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
