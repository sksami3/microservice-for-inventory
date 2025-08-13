package com.example.stock_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Using values injected from application.properties
        dataSource.setDriverClassName(driverClassName);  // e.g., com.mysql.cj.jdbc.Driver
        dataSource.setUrl(dataSourceUrl);  // e.g., jdbc:mysql://localhost:3306/your_db
        dataSource.setUsername(dataSourceUsername);  // e.g., root
        dataSource.setPassword(dataSourcePassword);  // e.g., 123456

        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
