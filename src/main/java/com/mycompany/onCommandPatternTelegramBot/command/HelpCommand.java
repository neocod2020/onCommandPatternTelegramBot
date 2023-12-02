package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.HELP;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.START;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STOP;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Help {@link Command}
 */
public class HelpCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    
    public final static String HELP_MESSAGE = String.format("<b>Available commands:</b>\n\n"
        + "<b>Start\\Stop bot's work</b>\n"
        + "%s - start to work with the Bot\n"
        + "%s - stop to work with the Bot\n"
        + "%s - how to work with the Bot\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
    sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
