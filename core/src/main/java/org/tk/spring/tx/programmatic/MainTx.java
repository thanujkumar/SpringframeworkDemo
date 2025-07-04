package org.tk.spring.tx.programmatic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTx {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TxJavaConfig.class);
        for (String name : annotationConfigApplicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        BusinessService businessService = annotationConfigApplicationContext.getBean("txService", BusinessService.class);
        businessService.doTxWork();

        try {
            businessService.doTxWorkThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        businessService.doTxWorkTxStatus();
    }
}
