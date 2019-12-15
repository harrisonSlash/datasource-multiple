package com.slash.datasources.multiple.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    basePackages = "com.slash.datasources.multiple.dao.heimdal",
    entityManagerFactoryRef = "heimdalEntityManager",
    transactionManagerRef = "heimdalTransactionManager"
)
public class HeimdalDataConfig {
    
    /*@Autowired
    private EntityManagerFactoryBuilder builder;*/

    @Bean
    @ConfigurationProperties("heimdal.datasource")
    public DataSourceProperties heimdalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource heimdalDataSource() {
        return heimdalDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean heimdalEntityManager() {
        /*return builder
            .dataSource(heimdalDataSource())
            .properties(getHibernateProperties())
            .packages(new String[] { "com.slash.datasources.multiple.domain.heimdal" })
            .persistenceUnit("heimdal")
            .build();*/
            
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(heimdalDataSource());
        em.setPackagesToScan(new String[] { "com.slash.datasources.multiple.domain.heimdal" });
    
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(getHibernateProperties());
    
        return em;
    }

    @Bean
    public PlatformTransactionManager heimdalTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(heimdalEntityManager().getObject());

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