package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.bot.OnCommandPatternTelegramBot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ААФ
 */
@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {
    
    private SendBotMessageService sendBotMessageService;
    private OnCommandPatternTelegramBot onCommandPatternTelegramBot;
    
//    public SendBotMessageServiceTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
    
    @BeforeEach
    public void setUp() {
        onCommandPatternTelegramBot = Mockito.mock(OnCommandPatternTelegramBot.class);
        sendBotMessageService = new com.mycompany.onCommandPatternTelegramBot.service
                .SendBotMessageServiceImpl(onCommandPatternTelegramBot);
    }
    
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Test of sendMessage method, of class SendBotMessageService.
     */
    @Test
    public void testSendMessage() throws TelegramApiException {
        //given
        System.out.println("sendMessage");
        String chatId = "test_chat_id";
        String message = "test_message";
        
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);
        
        //when
        sendBotMessageService.sendMessage(chatId, message);
        //then
        Mockito.verify(onCommandPatternTelegramBot).execute(sendMessage);
        
//        SendBotMessageService instance = new SendBotMessageServiceImpl();
//        instance.sendMessage(chatId, message);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

//    public class SendBotMessageServiceImpl implements SendBotMessageService {
//
//        public void sendMessage(String chatId, String message) {
//        }
//    }
    
}
