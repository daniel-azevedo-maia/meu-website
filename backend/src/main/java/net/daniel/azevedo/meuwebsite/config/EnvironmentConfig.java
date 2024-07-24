package net.daniel.azevedo.meuwebsite.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentConfig {

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @PostConstruct
    public void printEnvironmentVariables() {
//        System.out.println("DB_USERNAME: " + dbUsername);
//        System.out.println("DB_PASSWORD: " + dbPassword);
    }
}