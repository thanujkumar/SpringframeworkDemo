package org.tk.spring.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class DBJavaConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        var dataSource = databaseBuilder.setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql").build();
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        var template = new JdbcTemplate();
        template.setDataSource(dataSource());

        return template;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return  new DataSourceTransactionManager(dataSource());
    }
}
