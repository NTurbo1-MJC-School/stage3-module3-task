package com.mjc.school;

import com.mjc.school.config.ApplicationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationIntegrationTest {
    @Test
    void contextLoads() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Object o = context.getBean("newsController");
        Assertions.assertNotNull(o);
    }
}
