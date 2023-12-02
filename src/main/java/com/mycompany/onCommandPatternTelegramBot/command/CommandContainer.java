package com.mycompany.onCommandPatternTelegramBot.command;

//import com.google.common.collect.ImmutableMap;

import com.google.common.collect.ImmutableMap;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.HELP;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.NO;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.START;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STOP;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;

/**
 * Container of the (@link Command}s for to handling telegram commands
 */

public class CommandContainer {
    
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    
    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }
    
    public Command retrieveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
    
}
