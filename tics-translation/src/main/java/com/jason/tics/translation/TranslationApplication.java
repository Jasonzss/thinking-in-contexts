package com.jason.tics.translation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Jason
 * @since 2023/09/09 - 14:26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TranslationApplication {
    public static void main(String[] args) {
        SpringApplication.run(TranslationApplication.class, args);
    }
}
