package org.tk.spring.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class ProductionDSConfig {

    @Bean
    public DSBasedOnProfile dataSource() {
        return  new DSBasedOnProfile("production");
    }
}
