package org.tk.spring.wiring.ex7;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan //No argument, so scan current package and all sub-packages (package where @Configuration class exists)
//@ComponentScan(basePackages = {"org.tk.spring.wiring.ex7})
public class JavaDefaultWiringConfig {
}
