package com.jason.tics.translation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jason
 * @since 2023/09/09 - 14:26
 */
@SpringBootApplication(scanBasePackages = {"com.jason.tics"})
public class TranslationApplication {
    public static void main(String[] args) {
        SpringApplication.run(TranslationApplication.class, args);
    }
}
