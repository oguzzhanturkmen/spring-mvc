package com.springstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
public class RootConfig {

    @Autowired
    private Environment env;
    //JDBC, Hibernate

    //We need a SessionFactory for accessing database with Hibernate, it needs to be configured
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());//we need to set the datasource
        sessionFactoryBean.setPackagesToScan(new String[]{"com.springstudy"});//we need to set the package to scan
        sessionFactoryBean.setHibernateProperties(hibernateProperties());//we need to set the hibernate properties
        return sessionFactoryBean;
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));//we need to set the dialect
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));//we need to set show sql
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));//we need to set format sql
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));//we need to set
        return properties;
    }
    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));//we need to set the driver class name
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));//we need to set the url
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));//we need to set the username
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));//we need to set the password
        return dataSource;

    }
}
