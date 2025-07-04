package org.tk.spring.tx.declarative;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service("dTxService")
public class DeclarativeBusinessService {


    @Transactional
    public void doTxWork() {
        System.out.println("Completed business work");
    }

    @Transactional
    public void doTxWorkThrowException() {
        throw new RuntimeException("Something went wrong");
    }

}
