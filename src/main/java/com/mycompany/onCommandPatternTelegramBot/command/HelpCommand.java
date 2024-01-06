package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.ADD_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.HELP;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.LIST_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.START;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STATISTIC;
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
            + "\n%s - " + CommandDescription.START_DESCRIPTION.getDescription()
            + "\n%s - " + CommandDescription.STOP_DESCRIPTION.getDescription()
            + "\n\n<b>Working with group subscriptions:</b>\n"
            + "\n%s - " + CommandDescription.ADD_GROUP_SUB.getDescription()
            + "\n%s - " + CommandDescription.LIST_GROUP_SUB.getDescription()
            + "\n\n%s - " + CommandDescription.HELP_DESCRIPTION.getDescription()
            + "\n%s - " + CommandDescription.STATISTIC_DESCRIPTION.getDescription(),
            START.getCommandName(), STOP.getCommandName(), ADD_GROUP_SUB.getCommandName(),
             LIST_GROUP_SUB.getCommandName(), HELP.getCommandName(),
            STATISTIC.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
