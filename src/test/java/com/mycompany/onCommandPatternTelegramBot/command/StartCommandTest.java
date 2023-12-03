package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.START;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StartCommand.START_MESSAGE;}

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService);
    }  
    
}
