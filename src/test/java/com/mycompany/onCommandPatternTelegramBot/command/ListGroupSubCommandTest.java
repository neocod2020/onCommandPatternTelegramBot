package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.LIST_GROUP_SUB;
import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author ААФ
 */

@DisplayName("Unit-level testing for ListGroupSubCommandTest")
public class ListGroupSubCommandTest {
    
    /**
     * Test of execute method, of class ListGroupSubCommand.
     */
    @Test
    public void testProperlyShowsListGroupSub() {
        System.out.println("testProperlyShowsListGroupSub");
        //given
        TelegaUser telegaUser = new TelegaUser();
        telegaUser.setActives(true);
        telegaUser.setChatId("1");
        
        List<GroupSub> groupSubList = new ArrayList<>();
        groupSubList.add(populateGroupSub(1, "gs1"));
        groupSubList.add(populateGroupSub(2, "gs2"));
        groupSubList.add(populateGroupSub(3, "gs3"));
        
        telegaUser.setGroupSubs(groupSubList);
        
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegaUserService telegaUserService = Mockito.mock(TelegaUserService.class);
        
        Mockito.when(telegaUserService.findByChatId(telegaUser.getChatId()))
                .thenReturn(Optional.of(telegaUser));
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(Long.valueOf(telegaUser.getChatId()));
        Mockito.when(message.getText()).thenReturn(LIST_GROUP_SUB.getCommandName());
        Update update = new Update();
        update.setMessage(message);
        String collectedGroups = "Subscribes were founded: \n\n" + telegaUser.getGroupSubs().stream()
                .map(it -> "Group: " + it.getTitle() + ", ID = " + it.getId() + " \n")
                .collect(Collectors.joining());
        ListGroupSubCommand listCommand = new ListGroupSubCommand(sendBotMessageService, telegaUserService);
        
        // when
        listCommand.execute(update);
        //then
        Mockito.verify(sendBotMessageService).sendMessage(telegaUser.getChatId(), collectedGroups);
    }

    private GroupSub populateGroupSub(int i, String title) {
        GroupSub gs = new GroupSub();
        gs.setId(i);
        gs.setTitle(title);
        return gs;
    }
    
}
