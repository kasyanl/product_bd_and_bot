package by.kasyan.bot.bot;

import by.kasyan.core.bean.User;
import by.kasyan.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Service
@PropertySource("classpath:application.properties")
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String userName;
    @Value("${bot.token}")
    private String token;

    private UserService userService;

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
            Optional<User> optionalUser = userService.findById(update.getMessage().getMessageId());
            var sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            if (optionalUser.isPresent()) {
                var user = optionalUser.get();
                String actualPrice = "" + user.getName();
                sendMessage.setText("Пользователь: " + actualPrice);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                sendMessage.setText("Пользователя с таким ID не существует");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Autowired
    public void setProductService(UserService userService) {
        this.userService = userService;
    }
}