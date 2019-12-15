package com.slash.datasources.multiple.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:datasources.properties"})
@EnableAutoConfiguration
@EnableJpaRepositories(
    basePackages = "com.slash.datasources.multiple.dao.hades",
    entityManagerFactoryRef = "hadesEntityManager",
    transactionManagerRef = "hadesTransactionManager"
)
public class HadesDataConfig {

    @Bean
    @Primary
    @ConfigurationProperties("hades.datasource")
    public DataSourceProperties hadesDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource hadesDataSource() {
        return hadesDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean hadesEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(hadesDataSource());
        em.setPackagesToScan(new String[] { "com.slash.datasources.multiple.domain.hades" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(getHibernateProperties());
 
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager hadesTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(hadesEntityManager().getObject());

        return transactionManager;
    }

    private Map<String, Object> getHibernateProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show-sql", true);
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }
}