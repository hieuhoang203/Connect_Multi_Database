package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.example.demo.entity")
@EnableJpaRepositories(
        basePackages = "com.example.demo.repositories.live",
        entityManagerFactoryRef = "liveEntityManagerFactory",
        transactionManagerRef = "liveTransactionManager"
)
public class LiveDatasourceConfig {

    @Primary
    @Bean(name = "liveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean liveEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("liveDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.entity")
                .persistenceUnit("live")
                .build();
    }

    @Primary
    @Bean(name = "liveTransactionManager")
    public PlatformTransactionManager liveTransactionManager(
            @Qualifier("liveEntityManagerFactory") EntityManagerFactory liveEntityManagerFactory) {
        return new JpaTransactionManager(liveEntityManagerFactory);
    }

}
