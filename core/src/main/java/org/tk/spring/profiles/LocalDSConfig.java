package org.tk.spring.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("embedded")
public class LocalDSConfig {

    @Bean
    public DSBasedOnProfile dataSource() {
      return  new DSBasedOnProfile("embedded");
    }
}
