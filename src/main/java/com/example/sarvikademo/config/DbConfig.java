package com.example.sarvikademo.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource(value = {"classpath:application.properties"})
@Configuration
public class DbConfig {

    private final String url = "jdbc:mysql://localhost:3306/menagerie?autoReconnect=true&createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci";
    private final String username = "root";
    private final String password = "password";


    @Value("${jdbc.driverClassName}")
    private String driverClass;
    @Value("${hibernate.dialect}")
    private String dialect;


    @Bean
    public DataSource getDataSource() {

        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setDriverClassName(driverClass);

        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(getDataSource());
        factory.setHibernateProperties(hibernateProperties());
        factory.setPackagesToScan(new String[]{"com.example.sarvikademo.modal"});
        return factory;
    }


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory factory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(factory);
        return transactionManager;
    }
}
