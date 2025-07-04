package org.tk.spring.tx.declarative;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableTransactionManagement //<tx:annotation-driven transaction-manager="txManager"/>
public class TxDeclarativeJavaConfig {
}
