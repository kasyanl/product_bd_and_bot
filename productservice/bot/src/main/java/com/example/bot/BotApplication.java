package com.example.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("by.kasyan.database")
public class BotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);

    }
}
