package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.bot.OnCommandPatternTelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {
    
    @Autowired
    private final OnCommandPatternTelegramBot onCommandPatternTelegramBot;

    
    public SendBotMessageServiceImpl(OnCommandPatternTelegramBot onCommandPatternTelegramBot) {
        this.onCommandPatternTelegramBot = onCommandPatternTelegramBot;
    }    

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        
        try {
            onCommandPatternTelegramBot.execute(sendMessage);
        } catch (TelegramApiException ex) {
            log.error("Error sending message " + ex.getMessage());
        }
    }
    
}
