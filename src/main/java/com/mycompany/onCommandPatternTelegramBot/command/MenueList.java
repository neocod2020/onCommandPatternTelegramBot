package com.mycompany.onCommandPatternTelegramBot.command;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

/**
 *
 * @list of BotCommands for to create menue of commands
 */
@Slf4j
public class MenueList {

    private final List<BotCommand> botCommands = new ArrayList<>();

    public List<BotCommand> getBotCommands() {
        for (CommandName s : CommandName.values()) {
            botCommands.add(new BotCommand(s.getSimpleName(), s.getDescription()));
        }
        return botCommands;
    }
}
