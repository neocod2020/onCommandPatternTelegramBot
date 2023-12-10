package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STATISTIC;
import static com.mycompany.onCommandPatternTelegramBot.command.StatistCommand.STATISTIC_MESSAGE;


public class StatistCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return STATISTIC.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(STATISTIC_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatistCommand(sendBotMessageService, telegaUserService);
    }
    
    
}
