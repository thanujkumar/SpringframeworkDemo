package org.tk.spring.tx.declarative;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

@Component("dummyTxMgr")
public class DummyPlatformTransactionManager implements PlatformTransactionManager, InitializingBean {


    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
        System.out.println("DummyPlatformTransactionManager.getTransaction");

        return new TransactionStatus() {

            private boolean isRollbackOnly;

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
            public void rollbackToSavepoint(Object savepoint) throws TransactionException {
                System.out.println("TransactionStatus.rollbackToSavepoint");
            }

            @Override
            public void releaseSavepoint(Object savepoint) throws TransactionException {
                System.out.println("TransactionStatus.releaseSavepoint");
            }

            @Override
            public boolean isNewTransaction() {
                System.out.println("TransactionStatus.isNewTransaction");
                return true;
            }

            @Override
            public void setRollbackOnly() {
                System.out.println("TransactionStatus.setRollbackOnly");
                isRollbackOnly = true;
            }

            @Override
            public boolean isRollbackOnly() {
                System.out.println("TransactionStatus.isRollbackOnly");
                return isRollbackOnly;
            }

            @Override
            public boolean isCompleted() {
                System.out.println("TransactionStatus.isCompleted");
                return false;
            }
        };
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        System.out.println("DummyPlatformTransactionManager.commit");
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        System.out.println("DummyPlatformTransactionManager.rollback");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("DummyPlatformTransactionManager intialised");
    }
}
