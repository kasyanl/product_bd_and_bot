package com.example.bot.bot;

import kasyan.springweb.bean.Product;
import kasyan.springweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Service
@ComponentScan(basePackages = "kasyan.springweb")
@PropertySource("classpath:application.properties")
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String userName;
    @Value("${bot.token}")
    private String token;

    private ProductService productService;

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update != null && update.hasMessage()) {
            Optional<Product> optionalProduct = productService.findByName(update.getMessage().getText());
            var sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            if (optionalProduct.isPresent()) {
                var product = optionalProduct.get();
                String actualPrice = "" + product.getActualPrice();
                sendMessage.setText("Стоимость составляет: " + actualPrice + " рублей");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                sendMessage.setText("нет такого продукта!");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
