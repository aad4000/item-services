package com.example.item_services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemServicesApplication {

    public static void main(String[] args) {
        // Load .env file
        Dotenv dotenv = Dotenv.load();

        // Set Mongo URI in System Properties (Spring Boot reads from System Properties by default)
        System.setProperty("spring.security.user.name", dotenv.get("SPRING_SECURITY_USER_NAME"));
        System.setProperty("spring.security.user.password", dotenv.get("SPRING_SECURITY_USER_PASSWORD"));
        System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGO_URI"));

        SpringApplication.run(ItemServicesApplication.class, args);
    }
}
