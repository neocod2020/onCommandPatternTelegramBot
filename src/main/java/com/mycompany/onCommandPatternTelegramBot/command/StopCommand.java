package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}
 */
public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegaUserService telegaUserService;
    public final static String STOP_MESSAGE = "All your susbscribes are non-active \uD830\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegaUserService telegaUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegaUserService = telegaUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
        telegaUserService.findByChatId(update.getMessage().getChatId().toString())
                .ifPresent(it -> {
                    it.setActives(false);
                    telegaUserService.save(it);
                });
    }
}
