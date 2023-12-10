package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STOP;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StopCommand.STOP_MESSAGE;}

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegaUserService);
    }  
    
}
