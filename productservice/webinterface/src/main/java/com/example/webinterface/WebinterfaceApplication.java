package com.example.webinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"by.kasyan.database"})
public class WebinterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebinterfaceApplication.class, args);
    }

}
