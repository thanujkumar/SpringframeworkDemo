package org.tk.spring.jpa;

import jakarta.persistence.PersistenceUnitTransactionType;
import org.hibernate.jpa.HibernatePersistenceProvider;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

//https://www.baeldung.com/java-bootstrap-jpa
public class HibernateH2PersistentContext implements PersistenceUnitInfo {

    //jakarta Persistence 3.1 was released in the spring of 2022 as part of Jakarta EE 10.[3] Jakarta Persistence 3.2 was released in spring 2024,
    // targeting inclusion in Jakarta EE 11.[4] EclipseLink and Hibernate are compatible implementations.

    public static final String JPA_VERSION = "3.2";
    private final String persistenceUnitName;
    private PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
    private DataSource jtaDataSource;
    private DataSource nonjtaDataSource;
    private final List<String> mappingFileNames = new ArrayList<>();
    private final Properties properties;
    private final List<String> managedClassNames;
    private final List<ClassTransformer> transformers = new ArrayList<>();

    public HibernateH2PersistentContext(String persistenceUnitName, List<String> managedClassNames, Properties properties) {
        this.persistenceUnitName = persistenceUnitName;
        this.managedClassNames = managedClassNames;
        this.properties = properties;
    }

    @Override
    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    @Override
    public String getPersistenceProviderClassName() {
        return HibernatePersistenceProvider.class.getName();
    }

    @Override
    public String getScopeAnnotationName() {
        return "";
    }

    @Override
    public List<String> getQualifierAnnotationNames() {
        return List.of();
    }

    @Override
    public jakarta.persistence.spi.PersistenceUnitTransactionType getTransactionType() {
        return jakarta.persistence.spi.PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    public PersistenceUnitInfo setJtaDataSource(DataSource jtaDataSource) {
        this.jtaDataSource = jtaDataSource;
        this.nonjtaDataSource = null;
        transactionType = PersistenceUnitTransactionType.JTA;
        return this;
    }

    @Override
    public DataSource getJtaDataSource() {
        return jtaDataSource;
    }

    public PersistenceUnitInfo setNonJtaDataSource(DataSource nonjtaDataSource) {
        this.nonjtaDataSource = nonjtaDataSource;
        this.jtaDataSource = null;
        transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
        return this;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return nonjtaDataSource;
    }

    @Override
    public List<String> getMappingFileNames() {
        return mappingFileNames;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return Collections.emptyList();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return managedClassNames;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return SharedCacheMode.UNSPECIFIED;
    }

    @Override
    public ValidationMode getValidationMode() {
        return ValidationMode.AUTO;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return JPA_VERSION;
    }

    @Override
    public ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {
        transformers.add(transformer);
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
