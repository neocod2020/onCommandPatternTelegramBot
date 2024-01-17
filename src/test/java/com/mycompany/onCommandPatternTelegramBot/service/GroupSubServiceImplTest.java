package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_group_subscription.GroupDiscussionInfo;
import com.mycompany.onCommandPatternTelegramBot.repository.GroupSubRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for GroupSubService")
public class GroupSubServiceImplTest {
    
    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private TelegaUser newUser;
    
    private final static String CHAT_ID = "1";    
   
    @BeforeEach
    public void init() {
        System.out.println("init");
        
        TelegaUserService telegaUserService = Mockito.mock(TelegaUserService.class);
        groupSubRepository = Mockito.mock(GroupSubRepository.class);
        groupSubService = new GroupSubServiceImpl(groupSubRepository, telegaUserService);
        
        newUser = new TelegaUser();
        newUser.setActives(true);
        newUser.setChatId(CHAT_ID);
        
        Mockito.when(telegaUserService.findByChatId(CHAT_ID)).thenReturn(Optional.of(newUser));
    }   
    
    /**
     * Test of save method, of class GroupSubServiceImpl.
     */
    @Test
    public void testProperlySaveGroup() {
        System.out.println("testProperlySaveGroup");
        
        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");
        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(newUser);
        
        // when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);
        
        //then
        Mockito.verify(groupSubRepository).save(expectedGroupSub);        
    }
    @Test
    public void testProperlyAddUserToExistingGroup() {
        System.out.println("testProperlyAddUserToExistingGroup");
        // given
        TelegaUser oldUser = new TelegaUser();
        oldUser.setChatId("2");
        oldUser.setActives(true);
        
        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");
        
        GroupSub groupFromDB = new GroupSub();
        groupFromDB.setId(groupDiscussionInfo.getId());
        groupFromDB.setTitle(groupDiscussionInfo.getTitle());
        groupFromDB.addUser(oldUser);
        
        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId()))
                .thenReturn(Optional.of(groupFromDB));
        
        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(oldUser);
        expectedGroupSub.addUser(newUser);
        
        // when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);
        // then
        Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }
}
