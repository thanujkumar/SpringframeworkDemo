package org.tk.spring.jpa2;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.resource.jdbc.spi.PhysicalConnectionHandlingMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories // required for spring-data-jpa (StockJ2pa2Repository)
@ComponentScan
public class Jpa2JavaConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        var dataSource = databaseBuilder.setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql").build();
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource()); //with setJtaDataSource we get error
        entityManagerFactoryBean.setPersistenceUnitName("TEST");
        entityManagerFactoryBean.setPackagesToScan(Stock2Entity.class.getPackage().getName());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        //https://github.com/spring-projects/spring-framework/issues/25858
//        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.transaction.jta.platform", "org.springframework.transaction.jta.JtaTransactionManager");
        entityManagerFactoryBean.getJpaPropertyMap().put(AvailableSettings.CONNECTION_HANDLING, PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION);
        entityManagerFactoryBean.getJpaPropertyMap().put(AvailableSettings.FORMAT_SQL, Boolean.TRUE);
        entityManagerFactoryBean.getJpaPropertyMap().put(AvailableSettings.USE_SQL_COMMENTS, Boolean.TRUE);
        entityManagerFactoryBean.getJpaPropertyMap().forEach((s, o) -> {
            System.out.println(s + "=" + o);
        });
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(/*EntityManagerFactory entityManagerFactory*/) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //transactionManager.setEntityManagerFactory(entityManagerFactory); or directly to above constructor
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        return transactionManager;
    }

}
