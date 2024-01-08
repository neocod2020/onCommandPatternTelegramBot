package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start (@link Command)
 */
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegaUserService telegaUserService;

    public final static String START_MESSAGE = "Hello! A'm Bot.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegaUserService telegaUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegaUserService = telegaUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        telegaUserService.findByChatId(chatId).ifPresentOrElse(
                // if present:
                user -> {
                    user.setActives(true);
                    telegaUserService.save(user);
                },
                // else:
                () -> {
                    TelegaUser telegaUser = new TelegaUser();
                    telegaUser.setActives(true);
                    telegaUser.setChatId(chatId);
                    telegaUserService.save(telegaUser);
                });

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }

}
