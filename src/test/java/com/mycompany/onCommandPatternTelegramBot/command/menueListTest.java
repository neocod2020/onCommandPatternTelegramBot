package com.mycompany.onCommandPatternTelegramBot.command;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

/**
 *
 * @author ААФ
 */
public class menueListTest {
    
    public menueListTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getBotCommands method, of class MenueList.
     */
   // @Test
    public void testGetBotCommands() {
        System.out.println("getBotCommands");
        MenueList instance = new MenueList();
        // List<BotCommand> expResult = null;
        List<BotCommand> result = instance.getBotCommands();
        //assertEquals(expResult, result);
        System.out.println(result);
    }
    
}
