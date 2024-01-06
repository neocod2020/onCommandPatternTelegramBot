package com.mycompany.onCommandPatternTelegramBot.command;


import com.google.common.collect.ImmutableMap;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.ADD_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.HELP;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.LIST_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.NO;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.START;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STATISTIC;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STOP;
import com.mycompany.onCommandPatternTelegramBot.jRClient.JrGroupClient;
import com.mycompany.onCommandPatternTelegramBot.service.GroupSubService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;

/**
 * Container of the (@link Command}s for to handling telegram commands
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;   

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegaUserService telegaUserService,
            JrGroupClient jrGroupClient, GroupSubService groupSubService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegaUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegaUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STATISTIC.getCommandName(), new StatistCommand(sendBotMessageService, telegaUserService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegaUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, jrGroupClient, groupSubService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
