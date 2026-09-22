package com.rightmove.hexagonaldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rightmove.hexagonaldemo")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
