package com.mycompany.onCommandPatternTelegramBot.command;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit-level testing for StopCommand")
public class UnknownCommandTest extends AbstractCommandTest{
    
    @Override
    String getCommandName() {
        return "/unnknowncommanda";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_MESSAGE;}

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }  
    
}
