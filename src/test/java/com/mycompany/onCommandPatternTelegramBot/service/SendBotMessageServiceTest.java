package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.bot.OnCommandPatternTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    public void setUp() {
        onCommandPatternTelegramBot = Mockito.mock(OnCommandPatternTelegramBot.class);
        sendBotMessageService = new com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageServiceImpl(onCommandPatternTelegramBot);
    }

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
    }
}
