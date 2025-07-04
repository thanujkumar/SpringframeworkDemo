package org.tk.spring.tx.programmatic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@Service("txService")
public class BusinessService {

    private final TransactionTemplate transactionTemplate;

    //Automatically injected if only one constructor exists
    public BusinessService(PlatformTransactionManager platformTransactionManager) {
        transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setTimeout(30);
    }

    public void doTxWork() {
        String message = transactionTemplate.execute(transactionStatus -> {
            return "Completed business work in transactionTemplate";
        });
        System.out.println(message);
    }

    public void doTxWorkThrowException() {
        transactionTemplate.execute(transactionStatus -> {
            throw new RuntimeException("Something went wrong");
        });
    }

    public void doTxWorkTxStatus() {
        transactionTemplate.execute(transactionStatus -> {
            try {
                throw new RuntimeException("Something went wrong, handling rollback through TransactionStatus");
            } catch (RuntimeException e) {
                e.printStackTrace();
                transactionStatus.setRollbackOnly();
            }
            return null;
        });

    }
}
