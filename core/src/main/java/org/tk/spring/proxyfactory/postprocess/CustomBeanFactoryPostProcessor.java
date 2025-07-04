package org.tk.spring.proxyfactory.postprocess;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

//This is different from BeanPostProcessor
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private boolean definitionExists(Class<?> clazz, ConfigurableListableBeanFactory r) {
        boolean exists = r.getBeansOfType(clazz).size() != 0;
        System.out.println(clazz.getSimpleName() + " is " + (exists ? " " : " not ") + "already registered");
        return exists;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Assert.isInstanceOf(BeanDefinitionRegistry.class, beanFactory);
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;

        // equivalent to <bean class=".....AnnotationAwareAspectJAutoProxyCreator" /> or @EnableAspectJAutoProxy
        if (!definitionExists(AnnotationAwareAspectJAutoProxyCreator.class, beanFactory)) {
            BeanDefinitionReaderUtils.registerWithGeneratedName(new RootBeanDefinition(AnnotationAwareAspectJAutoProxyCreator.class), beanDefinitionRegistry);
        }

        // equivalent to <bean class=".....MethodTimeLoggingAspect" />
        if (!definitionExists(MethodTimeLoggingAspect.class, beanFactory)) {
            BeanDefinitionReaderUtils.registerWithGeneratedName(new RootBeanDefinition(MethodTimeLoggingAspect.class), beanDefinitionRegistry);
        }
    }
}
