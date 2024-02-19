package com.jason.tics.test.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Jason
 */
@SpringBootApplication(scanBasePackages = {"com.jason.tics"})
@EnableCaching
public class TestApplicationA {
    public static void main(String[] args) {
        SpringApplication.run(TestApplicationA.class, args);
    }
}
