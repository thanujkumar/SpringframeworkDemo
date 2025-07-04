package org.tk.spring.scope;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
//@ComponentScan
public class ScopeJavaConfig {

    //Register Custom scope with container (should be done before beanFactory initializes -->
    //method is static to register first
    @Bean
    public static CustomScopeConfigurer configurer() {
        CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> scopeMap = new ConcurrentHashMap<>();
        scopeMap.put("thread", scope());

        scopeConfigurer.setScopes(scopeMap);
        return scopeConfigurer;
    }

    @Bean //custom scope
    public static SimpleThreadScope scope() {
        return new SimpleThreadScope();
    }

    @Bean
    @Scope("thread") //custom scope name
    public ThreadAnnouncer announcer() {
        return new ThreadAnnouncer();
    }

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        return taskExecutor;
    }
}
