package org.tk.spring.beandefinition;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Data
public class AnotherMyBean implements BeanPostProcessor {
    private String name;
    private String version;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=========AnotherMyBean.postProcessAfterInitialization="+beanName);
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AnotherMyBean) {
            AnotherMyBean anotherMyBean = (AnotherMyBean) bean;
            anotherMyBean.setName("TestMe");
            anotherMyBean.setVersion("This value set from BeanPostProcessor");
        }
        System.out.println("=========AnotherMyBean.postProcessBeforeInitialization="+beanName);
        return bean;
    }
}
