package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.bot.OnCommandPatternTelegramBot;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


abstract class AbstractCommandTest {
    
    protected OnCommandPatternTelegramBot onCommandPatternTelegramBot = Mockito.mock(OnCommandPatternTelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(onCommandPatternTelegramBot);
     
    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();
    
    /**
     * Test of retrieveCommand method, of class CommandContainer.
     */
    @Test
    public void testProperlyExecuteCommand() {
        System.out.println("testExecuteCommand " + getCommandName());
        //given
        Long chatId = 12336544789L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);
        
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);
        
        //when
        getCommand().execute(update);
        
        //then
        Mockito.verify(onCommandPatternTelegramBot).equals(sendMessage);
        
    }    
    
}
