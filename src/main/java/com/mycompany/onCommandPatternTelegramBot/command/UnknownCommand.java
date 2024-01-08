package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Unknown (@link Command)
 */
public class UnknownCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public final static String UNKNOWN_MESSAGE = "Can't recognize your command\n"
            + "Enter /help to see command menue";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }

}
