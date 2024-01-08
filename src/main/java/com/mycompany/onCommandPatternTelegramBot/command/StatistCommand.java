package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * StatistCommand (@link Command)
 */
public class StatistCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegaUserService telegaUserService;

    public final static String STATISTIC_MESSAGE = "This Bot use %s users.";

    public StatistCommand(SendBotMessageService sendBotMessageService, TelegaUserService telegaUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegaUserService = telegaUserService;
    }

    @Override
    public void execute(Update update) {
        String activesUsersCount = String.valueOf(telegaUserService.retrieveAllActivesUsers().size());

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                 String.format(STATISTIC_MESSAGE, activesUsersCount));
    }

}
