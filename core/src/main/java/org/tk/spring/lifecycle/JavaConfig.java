package org.tk.spring.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"org.tk.spring.lifecycle"})
public class JavaConfig {

      public JavaConfig() {
          System.out.println("===JavaConfig constructor called (no lifecycle added)===============");
      }
//    @Bean(initMethod = "customInit") //can use @PostConstruct for customInit
//    public BeanLifecycle beanLifecycle() {
//        return new BeanLifecycle(); // If object is created directly BeanPostProcessor is not called, so called when component scan loads the bean
//    }
}