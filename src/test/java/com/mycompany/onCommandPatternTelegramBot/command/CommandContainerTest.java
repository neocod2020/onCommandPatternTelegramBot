package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.jRClient.JrGroupClient;
import com.mycompany.onCommandPatternTelegramBot.service.GroupSubService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.StatisticsService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import java.util.Arrays;
import static java.util.Collections.singletonList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {
    
    private CommandContainer commandContainer;
     
    @BeforeEach
    public void setUp() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegaUserService telegaUserService = Mockito.mock(TelegaUserService.class);
        JrGroupClient jrGroupClient = Mockito.mock(JrGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        StatisticsService statisticsService = Mockito.mock(StatisticsService.class);
        commandContainer = new CommandContainer(sendBotMessageService, telegaUserService, 
                jrGroupClient, groupSubService, singletonList("username"), statisticsService);
    }    
    
    /**
     * Test of retrieveCommand method, of class CommandContainer.
     */
    @Test
    public void testRetrieveCommand() {
        System.out.println("retrieveCommand");

        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                Command command = commandContainer.retrieveCommand(commandName.getCommandName(), "username");
                Assertions.assertNotSame(UnknownCommand.class, command.getClass());
                });
    }
    
    /**
     * Test of return UnknownCommand method, of class CommandContainer.
     */
    @Test
    public void shouldReturnUnknownCommand() {
        System.out.println("shouldReturnUnknownCommand");
        //given
        String unknownCommand = "/qwerty";
        //when
        Command command = commandContainer.retrieveCommand(unknownCommand, "username");
        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
