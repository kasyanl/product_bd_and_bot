package com.example.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages= {"by.kasyan.database", "com.example.bot"})
//@ComponentScan("by.kasyan.database")
public class BotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);

    }
}
