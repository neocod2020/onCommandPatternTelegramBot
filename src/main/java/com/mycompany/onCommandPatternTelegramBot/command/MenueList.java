package com.mycompany.onCommandPatternTelegramBot.command;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

/**
 *
 * @list of BotCommands for to create menue of commands
 */
public class MenueList {

    private final List<BotCommand> botCommands = new ArrayList<>();
    private final List<String> commandNames = CommandName.getNames();
    private final List<String> descriptions = CommandDescription.getDescriptions();

    public List<BotCommand> getBotCommands() {
        for (String s : commandNames) {
            for (String s1 : descriptions) {
                if (s1.contains(s.substring(1))) {
                    botCommands.add(new BotCommand(s, s1));
                }
            }
        }
        return botCommands;
    }
}
