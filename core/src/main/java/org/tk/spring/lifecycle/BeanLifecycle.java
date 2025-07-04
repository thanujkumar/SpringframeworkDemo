package org.tk.spring.lifecycle;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * As defined in  {@link org.springframework.beans.factory.BeanFactory}
 * https://www.tutorialspoint.com/spring/spring_ioc_containers.htm
 * <ol>
 * <li>  instantiate</li>
 * <li>  populate properties</li>
 * <li>  BeanNameAware</li>
 * <li>  BeanFactoryAware</li>
 * <li>  Pre-initialization - BeanPostProcessor</li>
 * <li>  InitializeBean</li>
 * <li> . init-method</li>
 * <li>  Post-initialization - BeanPostProcessor</li>
 * <li> . ....</li>
 * </ol>
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class BeanLifecycle implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware,
        MessageSourceAware, ApplicationContextAware, InitializingBean, DisposableBean {

    public BeanLifecycle() {
        System.out.println("===BeanLifecycle constructor called===============");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("1: BeanNameAware.setBeanName :=" + s);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("2: BeanClassLoaderAware.setBeanClassLoader :=" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3: BeanFactoryAware.setBeanFactory :=" + beanFactory);
    }


    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("4: EnvironmentAware.setEnvironment :=" + environment);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        System.out.println("5: EmbeddedValueResolverAware.setEmbeddedValueResolver :=" + stringValueResolver);
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("6: ResourceLoaderAware.setResourceLoader :=" + resourceLoader);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("7: ApplicationEventPublisherAware.setApplicationEventPublisher :=" + applicationEventPublisher);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("8: MessageSourceAware.setMessageSource :=" + messageSource);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("9: ApplicationContextAware.setApplicationContext :=" + applicationContext);
        System.out.println("10: ServletContextAware.setApplicationContext := Applicable only in web environment");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("13: InitializingBean.afterPropertiesSet ");
    }


    @PostConstruct
    private void customInit() {
        System.out.println("12: Custom init-method as defined @Bean(initMethod = \"customInit\")  or @PostConstruct");
    }


    @Override
    public void destroy() {
        System.out.println("16: Called DisposableBean.destory method ");
    }


    @PreDestroy
    private void preDestroy() {
        System.out.println("15: Called @PreDestroy annotated method ");
    }

}
