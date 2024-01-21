package com.mycompany.onCommandPatternTelegramBot.command;


import com.google.common.collect.ImmutableMap;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.ADD_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.DELETE_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.HELP;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.LIST_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.NO;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.START;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STATISTIC;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STOP;
import com.mycompany.onCommandPatternTelegramBot.command.annotation.AdminCommand;
import com.mycompany.onCommandPatternTelegramBot.jRClient.JrGroupClient;
import com.mycompany.onCommandPatternTelegramBot.service.GroupSubService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.StatisticsService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import java.util.List;
import static java.util.Objects.nonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Container of the (@link Command}s for to handling telegram commands
 */
@Slf4j
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;    

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegaUserService telegaUserService,
            JrGroupClient jrGroupClient, GroupSubService groupSubService, List<String> admins, 
            StatisticsService statisticsService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegaUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegaUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STATISTIC.getCommandName(), new StatistCommand(sendBotMessageService, statisticsService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegaUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, jrGroupClient, groupSubService))
                .put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, telegaUserService, groupSubService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
        this.admins = admins;
    }

    public Command retrieveCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        log.info("retrieveCommand, orDefault = " + commandIdentifier + " username = " + username);
        if(isAdminCommand(orDefault)){
            if(admins.contains(username)){                
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command){
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
