package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandDescription.ADD_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandDescription.HELP_DESCRIPTION;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandDescription.LIST_GROUP_SUB;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandDescription.START_DESCRIPTION;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandDescription.STATISTIC_DESCRIPTION;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandDescription.STOP_DESCRIPTION;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

@DisplayName("Unit-level testing for MenueList")
public class menueListTest {

    /**
     * Test of getBotCommands method, of class MenueList.
     */
    @Test
    public void testGetBotCommands() {
        System.out.println("getBotCommands");
        MenueList instance = new MenueList();
        List<BotCommand> expResult = Arrays.asList(
                new BotCommand("/start", START_DESCRIPTION.getDescription()),
                 new BotCommand("/stop", STOP_DESCRIPTION.getDescription()),
                 new BotCommand("/help", HELP_DESCRIPTION.getDescription()),
                 new BotCommand("/statistic", STATISTIC_DESCRIPTION.getDescription()),
                 new BotCommand("/list", LIST_GROUP_SUB.getDescription()),
                 new BotCommand("/add", ADD_GROUP_SUB.getDescription())
        );
        List<BotCommand> result = instance.getBotCommands();
        assertEquals(expResult, result);
    }

}
