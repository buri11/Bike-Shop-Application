package com.example.bikeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is the main application class
 */
@SpringBootApplication
public class BikeShopApplication {
    /**
     * This is the main method which is starts the application
     * @param args It contains the eventual arguments for the main method, but most of the time it is empty
     */
    public static void main(String[] args) {
        SpringApplication.run(BikeShopApplication.class, args);
    }

}
