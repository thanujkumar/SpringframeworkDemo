package org.tk.spring.wiring.ex1.javabased;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Without @Component this bean is not wired by @ComponentScan
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class IsThisBeanWired {
}
