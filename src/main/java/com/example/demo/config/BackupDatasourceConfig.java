package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.example.demo.backup.entity")
@EnableJpaRepositories(
        basePackages = "com.example.demo.backup.repositories",
        entityManagerFactoryRef = "backupEntityManagerFactory",
        transactionManagerRef = "backupTransactionManager"
)
public class BackupDatasourceConfig {

    @Bean(name = "backupEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean backupEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("backupDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.backup.entity")
                .persistenceUnit("backup")
                .build();
    }

    @Bean(name = "backupTransactionManager")
    public PlatformTransactionManager backupTransactionManager(
            @Qualifier("backupEntityManagerFactory") EntityManagerFactory backupEntityManagerFactory) {
        return new JpaTransactionManager(backupEntityManagerFactory);
    }

}
