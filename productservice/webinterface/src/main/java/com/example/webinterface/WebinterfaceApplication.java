package com.example.webinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"by.kasyan.database", "com.example.webinterface"})
public class WebinterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebinterfaceApplication.class, args);
    }

}
