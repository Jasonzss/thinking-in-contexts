package com.jason.tics.point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jason
 */
@SpringBootApplication(scanBasePackages = {"com.jason.tics"})
public class PointApplication {
    public static void main(String[] args) {
        SpringApplication.run(PointApplication.class, args);
    }
}
