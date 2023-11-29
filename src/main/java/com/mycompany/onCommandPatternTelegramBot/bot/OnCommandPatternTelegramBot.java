package com.mycompany.onCommandPatternTelegramBot.bot;



import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class OnCommandPatternTelegramBot extends TelegramLongPollingBot { 
    
    @Value("${bot.name}")
    private String username;
    
    @Value("${bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        log.info("onUpdateReceived");
    if(update.hasMessage() && update.getMessage().hasText()){
        String msg = update.getMessage().getText().trim();
        String chatId = update.getMessage().getChatId().toString();
        
        log.info("update.hasMessage(): " + msg + ", chatId = " + chatId);
        
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(msg);
        try {
            execute(sm);
        } catch (TelegramApiException ex) {
            log.error("Error executing send message: " + ex.getMessage());
        }
    } 
    
    }

    @Override
    public String getBotUsername() {
        return username;
    }
    
    @Override
    public String getBotToken() {
        return token;
    }
}
