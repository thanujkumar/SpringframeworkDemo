package org.tk.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

import java.util.Calendar;

public class CalendarFactory implements FactoryBean<Calendar> {
    private Calendar calendar = Calendar.getInstance();

    @Override
    public Calendar getObject() throws Exception {
        return calendar;
    }

    @Override
    public Class<?> getObjectType() {
        return Calendar.class;
    }

    public void addDays(int days) {
        calendar.add(Calendar.DAY_OF_YEAR, days);
    }
}
