package com.mjc.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = {"com.mjc.school"})
public class ApplicationConfig {
}
