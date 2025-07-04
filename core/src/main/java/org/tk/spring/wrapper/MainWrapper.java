package org.tk.spring.wrapper;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyEditor;
import java.util.Currency;

//https://www.logicbig.com/how-to/code-snippets/jcode-spring-framework-beanwrapper.html

public class MainWrapper {
    public static void main(String[] args) {
//        PropertyEditorSupport implements PropertyEditor;
//        SimpleBeanInfo

        BeanWrapperImpl wrapper = new BeanWrapperImpl();
        PropertyEditor editor = wrapper.getDefaultEditor(Currency.class);
        editor.setAsText("INR");
        Currency value = (Currency) editor.getValue();
        System.out.println(value.getDisplayName());
        System.out.println(value.getSymbol());

        //
        MyBean bean = new MyBean();
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(bean);
        beanWrapper.setPropertyValue("name", "Ajeeth");
        beanWrapper.setPropertyValue("age", 12);

        System.out.println(bean.getName());
        System.out.println(bean.getAge());
    }
}
