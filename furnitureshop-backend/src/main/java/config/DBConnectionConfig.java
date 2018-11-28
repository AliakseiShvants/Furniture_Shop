package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static util.Constants.*;

@Configuration
@PropertySource(DEV)
//@Profile("dev")
@EnableTransactionManagement
public class DBConnectionConfig {

    @Value("${" + DRIVER + "}")
    private String databaseDriver;

    @Value("${" + URL + "}")
    private String url;

    @Value("${" + SCHEMA + "}")
    private String schema;

    @Value("${" + USER + "}")
    private String user;

    @Value("${" + PASSWORD + "}")
    private String password;

    @Value("${" + DIALECT + "}")
    private String dialect;

    @Value("${" + DDL_AUTO + "}")
    private String generatedDdl;

    @Value("${" + SHOW_SQL + "}")
    private Boolean isShowSql;

    /**
     * <p>A static class that loads database property and injects values in appropriate fields.
     * Resolves ${...} placeholders within bean definition property values and @Value annotations.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * <p>A simple implementation of the standard JDBC DataSource interface, configuring the plain old JDBC DriverManager
     * via bean properties.
     *
     * @return A new Connection from every getConnection call.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setSchema(schema);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * <p>A specific implementation for Hibernate EntityManager.
     *
     * @return JPA adapter class.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        jpaVendorAdapter.setShowSql(isShowSql);
        return jpaVendorAdapter;
    }

    /**
     * <p>A class that creates a JPA EntityManagerFactory according to JPA's standard container bootstrap contract.
     * Sets up a shared JPA EntityManagerFactory in a Spring application context.
     *
     * @return a EntityManagerFactory object
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setDataSource(dataSource());
        factory.setPackagesToScan(ENTITIES_PACKAGES);
        return factory;
    }

    /**
     * <p>Binds a JPA EntityManager from the specified factory to the thread, potentially allowing for
     * one thread-bound EntityManager per factory.
     *
     * @return JPA transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}
