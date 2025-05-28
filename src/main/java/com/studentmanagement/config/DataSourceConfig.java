package com.studentmanagement.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean(name = "sqlServerDataSource")
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.sqlserver")
    public DataSource sqlServerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlServerJdbcTemplate")
    @Primary
    public JdbcTemplate sqlServerJdbcTemplate() {
        return new JdbcTemplate(sqlServerDataSource());
    }

    @Bean(name = "sqliteDataSource")
    @ConfigurationProperties(prefix = "app.datasource.sqlite")
    public DataSource sqliteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqliteJdbcTemplate")
    public JdbcTemplate sqliteJdbcTemplate() {
        return new JdbcTemplate(sqliteDataSource());
    }
}