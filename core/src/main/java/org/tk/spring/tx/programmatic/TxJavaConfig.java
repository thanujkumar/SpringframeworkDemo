package org.tk.spring.tx.programmatic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

@Configuration
@ComponentScan
public class TxJavaConfig {

    @Bean("transactionManager")
    public PlatformTransactionManager getPlatfromPlatformTransactionManager() {
        return new PlatformTransactionManager() {

            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
                System.out.println("PlatformTransactionManager.getTransaction");
                return new TransactionStatus() {
                    private boolean rollbackOnly = false;
                    @Override
                    public boolean hasSavepoint() {
                        System.out.println("TransactionStatus.hasSavepoint");
                        return false;
                    }

                    @Override
                    public void flush() {
                        System.out.println("TransactionStatus.flush");
                    }

                    @Override
                    public Object createSavepoint() throws TransactionException {
                        System.out.println("TransactionStatus.createSavepoint");
                        return null;
                    }

                    @Override
                    public void rollbackToSavepoint(Object o) throws TransactionException {
                        System.out.println("TransactionStatus.rollbackToSavepoint");
                    }

                    @Override
                    public void releaseSavepoint(Object o) throws TransactionException {
                        System.out.println("TransactionStatus.releaseSavepoint");
                    }

                    @Override
                    public boolean isNewTransaction() {
                        System.out.println("TransactionStatus.isNewTransaction");
                        return false;
                    }

                    @Override
                    public void setRollbackOnly() {
                        System.out.println("TransactionStatus.setRollbackOnly");
                        rollbackOnly = true;
                    }

                    @Override
                    public boolean isRollbackOnly() {
                        System.out.println("TransactionStatus.isRollbackOnly");
                        return rollbackOnly;
                    }

                    @Override
                    public boolean isCompleted() {
                        System.out.println("TransactionStatus.isCompleted");
                        return false;
                    }
                };
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("PlatformTransactionManager.commit");
            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("PlatformTransactionManager.rollback");
            }
        };
    }
}
