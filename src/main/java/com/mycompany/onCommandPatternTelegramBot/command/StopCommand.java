package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}
 */
public class StopCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    
    public final static String STOP_MESSAGE = "All your susbscribes are non-active \uD830\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
    sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
