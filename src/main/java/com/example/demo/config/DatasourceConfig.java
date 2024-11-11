package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("data-live.datasource")
    public DataSource liveDataSource() {
        return liveDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("data-live.datasource")
    public DataSourceProperties liveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("data-backup.datasource")
    public DataSource backupDataSource() {
        return backupDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("data-backup.datasource")
    public DataSourceProperties backupDataSourceProperties() {
        return new DataSourceProperties();
    }

}
