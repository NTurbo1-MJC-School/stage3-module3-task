package com.mjc.school.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Configuration
public class RepositoryConfig {

    @Bean
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory("pu")
                .createEntityManager();
    }
}
