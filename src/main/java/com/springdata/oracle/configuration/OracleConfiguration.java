package com.springdata.oracle.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:persistence-multiple-db.properties")
@EnableJpaRepositories(basePackages = "com.springdata.dao.oracle", entityManagerFactoryRef = "oracleEntityManager", transactionManagerRef = "oracleTransactionManager")
public class OracleConfiguration {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean oracleEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(myOracleDataSource());
        em.setPackagesToScan(new String[] { "com.springdata.models" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public DataSource myOracleDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.oracle.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.oracle.jdbc.url"));
        dataSource.setUsername(env.getProperty("spring.oracle.user"));
        dataSource.setPassword(env.getProperty("spring.oracle.pass"));

        return dataSource;
    }

    @Bean
    @Primary
    public PlatformTransactionManager oracleTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(oracleEntityManager().getObject());
        return transactionManager;
    }
}
