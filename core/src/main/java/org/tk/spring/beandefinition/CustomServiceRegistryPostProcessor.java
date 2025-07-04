package org.tk.spring.beandefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * https://blog.jdriven.com/2015/04/spicy-spring-dynamically-create-your-own-beandefinition/
 * When we a have Spring managed application, we want to let Spring manage all of our beans. Beside the regular way of
 * creating beans with known solutions like Annotated beans, Java Configuration and XML Configuration, there is also a
 * way in which we can create our own BeanDefinition. With a BeanDefinitionRegistryPostProcessor it is possible to create
 * a specific post processor which can add BeanDefinitions to the BeanDefinitionRegistry. It differs from the BeanPostProcessor,
 * which only has hooks for Bean Initialization (construction of your POJO), where the BeanDefinitionRegistryPostProcessor
 * has a hook on the BeanDefinitionRegistry. This gives us the ability to define our own BeanDefinition. First we
 * create a BeanDefinitionRegistryPostProcessor implementation as listed in the example. We implement the required method,
 * and will be able to add our own bean definition to the registry. The defined BeanDefinition will be picked up by
 * the ApplicationContext and the POJO will be constructed. Our result is A Spring managed bean
 */
@Component
public class CustomServiceRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor  {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(MyBean.class); // Actual service
        beanDefinition.setTargetType(MyBean.class); //The service interface
        beanDefinition.setRole(BeanDefinition.ROLE_APPLICATION);
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        registry.registerBeanDefinition("myBean2", beanDefinition );

        RootBeanDefinition anotherBeanDef = new RootBeanDefinition(AnotherMyBean.class); // Actual service
        anotherBeanDef.setTargetType(AnotherMyBean.class); //The service interface
        anotherBeanDef.setRole(BeanDefinition.ROLE_APPLICATION);
        anotherBeanDef.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        registry.registerBeanDefinition("anotherMyBean2", anotherBeanDef );
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("================postProcessBeanFactory : "+ beanFactory);
    }

    //BeanDefinition
}
