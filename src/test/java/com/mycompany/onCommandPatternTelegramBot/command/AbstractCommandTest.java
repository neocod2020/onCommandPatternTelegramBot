package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.bot.OnCommandPatternTelegramBot;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageServiceImpl;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


abstract class AbstractCommandTest {
    
    protected OnCommandPatternTelegramBot onCommandPatternTelegramBot = Mockito.mock(OnCommandPatternTelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(onCommandPatternTelegramBot);
    protected TelegaUserService telegaUserService = Mockito.mock(TelegaUserService.class);
    
    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();
    
    /**
     * Test of retrieveCommand method, of class CommandContainer.
     */
    @Test
    public void testProperlyExecuteCommand() throws TelegramApiException {
        System.out.println("testExecuteCommand " + getCommandName());
        //given
        Long chatId = 12336544789L;

        Update update = prepareUpdate(chatId, getCommandName());
        
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
        Mockito.verify(onCommandPatternTelegramBot).execute(sendMessage);        
    }    
    public static Update prepareUpdate(Long chatId, String commandName){
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(commandName);
        update.setMessage(message);
        return update;
    }
    
}
