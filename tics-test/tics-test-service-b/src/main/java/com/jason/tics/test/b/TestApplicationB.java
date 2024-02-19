package com.jason.tics.test.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Jason
 */
@SpringBootApplication(scanBasePackages = {"com.jason.tics"})
@EnableCaching
public class TestApplicationB {
    public static void main(String[] args) {
        SpringApplication.run(TestApplicationB.class, args);
    }
}
