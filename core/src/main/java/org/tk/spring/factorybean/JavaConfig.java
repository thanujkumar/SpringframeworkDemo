package org.tk.spring.factorybean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Calendar;

//https://www.baeldung.com/spring-bean-scopes

@Configuration
//@ComponentScan(basePackages = {"org.tk.spring.factorybean"})
public class JavaConfig {

    @Bean // Here as it is a factory returned object is of type Calendar and not CalendarFactory
    @Scope("prototype")
    public CalendarFactory getCalendarFactory() {
        CalendarFactory calendarFactory = new CalendarFactory();
        //addDays can be called only here
        calendarFactory.addDays(3);
        return calendarFactory;
    }

    @Bean
    //@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //below is assigned from ConfigurableBeanFactory
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Calendar getCalendar() throws Exception {
        return getCalendarFactory().getObject();
    }

    //SpEL
    @Value("#{T(java.lang.Math).random()*100.0}")
    private double randSeed;

    @Bean
    public Double getRandSeed() {
        return randSeed;
    }
}
