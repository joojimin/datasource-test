package com.example.datasourcetest.configuration;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.example.datasourcetest.domain")
@Configuration
public class DataSourceConfig {

    private final static String DOMAIN_PACKAGE_PATH = "com.example.datasourcetest";

    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                                .type(HikariDataSource.class)
                                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder) {
        Map<String, Object> hibernatePropertiesMap = hibernateProperties
            .determineHibernateProperties(jpaProperties.getProperties(),
                                          new HibernateSettings());
        return builder.dataSource(dataSource())
                      .properties(hibernatePropertiesMap)
                      .packages(DOMAIN_PACKAGE_PATH)
                      .persistenceUnit("master")
                      .build();
    }


    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        EntityManagerFactory entityManagerFactory = Objects.requireNonNull(entityManagerFactory(builder).getObject());
        return new JpaTransactionManager(entityManagerFactory);
    }
}
