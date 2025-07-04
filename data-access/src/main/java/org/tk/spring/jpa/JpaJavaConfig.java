package org.tk.spring.jpa;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories // required for spring-data-jpa (StockJpaRepository)
@ComponentScan
public class JpaJavaConfig implements ApplicationContextAware {

    //require spring-orm
    public JpaJavaConfig(Environment env) {
        Arrays.stream(env.getActiveProfiles()).forEach(x -> System.out.println("ActiveProfile: " + x));
        Arrays.stream(env.getDefaultProfiles()).forEach(x -> System.out.println("DefaultProfile : " + x));
        ((StandardEnvironment) env).getSystemEnvironment().forEach((s, o) -> System.out.println(s + " -=- " + o));
        ((StandardEnvironment) env).getSystemProperties().forEach((s, o) -> {
            System.out.println(s + " :=: " + o);
        });

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        var dataSource = databaseBuilder.setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql").build();
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernatePersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
        //https://www.baeldung.com/java-bootstrap-jpa
        //Build PersistentUnitInfo Programmatically
        Class[] entityClasses = new Class[]{StockEntity.class}; //Entities to be managed
        List<String> managedClasses = Arrays.asList(entityClasses).stream().map(Class::getName).collect(Collectors.toList());
        HibernateH2PersistentContext h2PersistentContext = new HibernateH2PersistentContext("JPA_ORM", managedClasses, getProperties());
        EntityManagerFactory entityManagerFactory =
                persistenceProvider.createContainerEntityManagerFactory(h2PersistentContext, h2PersistentContext.getProperties());
        return entityManagerFactory;
    }

    protected Properties getProperties() {
        Properties properties = new Properties();
        properties.put("connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("show_sql", "true");
        properties.put("hibernate.connection.datasource", dataSource());
        return properties;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        return transactionManager;
    }
}

/*
    The JPA specification defines two kinds of entity managers:

    Application-managed—Entity (LocalEntityManagerFactoryBean)  managers are created when an application directly requests
    one from an entity manager factory. With application-managed entity managers, the application is responsible for opening
    or closing entity managers and involving the entity manager in transactions. This type of entity manager is most
    appropriate for use in standalone applications that don’t run in a Java EE container.

    Container-managed—Entity (LocalContainerEntityManagerFactoryBean) managers are created and managed by a Java EE container.
    The application doesn’t interact with  the entity manager factory at all. Instead, entity managers are obtained
    directly through injection or from JNDI.  The container is responsible for configuring the entity manager factories.
    This type of entity manager is most appropriate for use by a Java EE container that wants to maintain some control
    over JPA configuration beyond what’s specified in persistence.xml.
 */