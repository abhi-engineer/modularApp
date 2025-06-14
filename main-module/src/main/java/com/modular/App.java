package com.modular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.modular")
@EntityScan(basePackages = "com.modular.entity")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

