package org.tk.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class ConfigureAop {

    @Bean(name = "aop")
    public ObjectUnderAop getObjectUnderAop() {
        return new ObjectUnderAop();
    }

}
