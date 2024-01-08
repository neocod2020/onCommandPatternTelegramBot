package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.AbstractCommandTest.prepareUpdate;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.DELETE_GROUP_SUB;
import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.service.GroupSubService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.singletonList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;


@DisplayName("Unit-level testing for DeleteGroupSubCommand")
public class DeleteGroupSubCommandTest {
    
    private Command command;
    private GroupSubService groupSubService;
    private  SendBotMessageService sendBotMessageService;
    private  TelegaUserService telegaUserService;
        
    @BeforeEach
    public void setUp() {
    sendBotMessageService = Mockito.mock(SendBotMessageService.class); 
    telegaUserService = Mockito.mock(TelegaUserService.class);
    groupSubService = Mockito.mock(GroupSubService.class);
    
    command = new DeleteGroupSubCommand(sendBotMessageService, telegaUserService, groupSubService);
    }
    
    /**
     * Test of execute method, of class DeleteGroupSubCommand.
     */
    @Test
    public void testProperlyReturnEmptySubscriptionList() {
        System.out.println("testProperlyReturnEmptySubscriptionList");
        // given
        Long chatId = 23456L;
        Update update = prepareUpdate(chatId, DELETE_GROUP_SUB.getCommandName());
        TelegaUser telegaUser = new TelegaUser();       
        telegaUser.setGroupSubs(Collections.EMPTY_LIST);
        Mockito.when(telegaUserService.findByChatId(String.valueOf(chatId)))
        .thenReturn(Optional.of(telegaUser));
        String expectedMessage = "You have not subscribes yet. To add subscription "
            + "send '/add' please.";
        //when
        command.execute(update);
        // then
        Mockito.verify(sendBotMessageService).sendMessage(String.valueOf(chatId), expectedMessage);
    }
    @Test
    public void testProperlyReturnSubscriptionList() {
        System.out.println("testProperlyReturnSubscriptionList");
        // given
        Long chatId = 23456L;
        Update update = prepareUpdate(chatId, DELETE_GROUP_SUB.getCommandName());
        TelegaUser telegaUser = new TelegaUser();
        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        telegaUser.setGroupSubs(singletonList(gs1));
        Mockito.when(telegaUserService.findByChatId(String.valueOf(chatId)))
        .thenReturn(Optional.of(telegaUser));
        String expectedMessage = "To remove subscription to the group please"
            + " send '/delete' and group ID \n"
            + "for example: /delete 16 \n\n"
            + "Down here list of your subscriptions: \n\n"
            + "Group title(name) - group ID \n\n"
            + "GS1 Title - 123 \n";
        //when
        command.execute(update);
        // then
        Mockito.verify(sendBotMessageService).sendMessage(String.valueOf(chatId), expectedMessage);
    }
    @Test
    public void testProperlyDeleteByGroupId() {
        System.out.println("testProperlyDeleteByGroupId");
        // given
        Long chatId = 23456L;
        Integer groupId = 1234;
        Update update = prepareUpdate(chatId, String.format("%s %s", DELETE_GROUP_SUB.getCommandName(), groupId));
        TelegaUser telegaUser = new TelegaUser();
        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        telegaUser.setChatId(String.valueOf(chatId));
        telegaUser.setGroupSubs(singletonList(gs1));
        List<TelegaUser> users = new ArrayList<>();
        users.add(telegaUser);
        gs1.setUsers(users);
        
         Mockito.when(groupSubService.findById(groupId))
        .thenReturn(Optional.of(gs1));
         
        Mockito.when(telegaUserService.findByChatId(String.valueOf(chatId)))
        .thenReturn(Optional.of(telegaUser));
        
        String expectedMessage = "Subscription to the group GS1 Title is deleted \n\n";
        //when
        command.execute(update);
        // then
        users.remove(telegaUser);
        Mockito.verify(groupSubService).save(gs1);
        Mockito.verify(sendBotMessageService).sendMessage(String.valueOf(chatId), expectedMessage);
    }
    @Test
    public void testDoesNotExistByGroupId() {
        System.out.println("testDoesNotExistByGroupId");
        // given
        Long chatId = 23456L;
        Integer groupId = 1234;
        Update update = prepareUpdate(chatId, String.format("%s %s", DELETE_GROUP_SUB.getCommandName(), groupId));
         
        Mockito.when(groupSubService.findById(groupId))
        .thenReturn(Optional.empty());
        
        String expectedMessage = "Group is not founded";
        //when
        command.execute(update);
        // then
        Mockito.verify(groupSubService).findById(groupId);
        Mockito.verify(sendBotMessageService).sendMessage(String.valueOf(chatId), expectedMessage);
    }
}
