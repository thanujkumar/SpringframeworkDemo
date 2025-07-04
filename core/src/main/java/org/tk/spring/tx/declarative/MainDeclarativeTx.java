package org.tk.spring.tx.declarative;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDeclarativeTx {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TxDeclarativeJavaConfig.class);
        for (String name : annotationConfigApplicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        DeclarativeBusinessService businessService = annotationConfigApplicationContext.getBean("dTxService", DeclarativeBusinessService.class);

        businessService.doTxWork();

        try {
            businessService.doTxWorkThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
