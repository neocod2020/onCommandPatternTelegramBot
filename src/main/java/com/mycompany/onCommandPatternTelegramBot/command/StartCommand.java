package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start (@link Command)
 */
public class StartCommand implements Command {
    
    private final SendBotMessageService sendBotMessageService;
    
    public final static String START_MESSAGE = "Hello! A'm Bot.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
    sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
    
    
    
}
