package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic.GroupStatDTO;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic.StatisticDTO;
import static java.util.Collections.singletonList;
import org.checkerframework.checker.units.qual.g;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for StatisticsService")
public class StatisticsServiceImplTest {
    
    private GroupSubService groupSubService;
    private TelegaUserService telegaUserService;
    private StatisticsService statisticsService;
    
    @BeforeEach
    public void setUp() {
        groupSubService = Mockito.mock(GroupSubService.class);
        telegaUserService = Mockito.mock(TelegaUserService.class);
        statisticsService = new StatisticsServiceImpl(groupSubService, telegaUserService);
    }    
    
    /**
     * Test of countBotStatistic method, of class StatisticsServiceImpl.
     */
    @Test
    public void testProperlySendStatDTO() {
        System.out.println("testProperlySendStatDTO");
        
        // given
        Mockito.when(telegaUserService.findAllInActivesUsers()).thenReturn(singletonList(new TelegaUser()));
        TelegaUser activeUser = new TelegaUser();
        activeUser.setGroupSubs(singletonList(new GroupSub()));
        Mockito.when(telegaUserService.findAllActivesUsers()).thenReturn(singletonList(activeUser));
        
        GroupSub groupSub = new GroupSub();
        groupSub.setTitle("group");
        groupSub.setId(1);
        groupSub.setUsers(singletonList(new TelegaUser()));
        Mockito.when(groupSubService.findAll()).thenReturn(singletonList(groupSub));        
       
        // when
        StatisticDTO result = statisticsService.countBotStatistic();
        
        // then
        assertNotNull(result);
        assertEquals(1, result.getActiveUserCount());
        assertEquals(1, result.getInactiveUserCount());
        assertEquals(1.0, result.getAverageGroupCountByUser());
        assertEquals(singletonList(new GroupStatDTO(groupSub.getId(), groupSub.getTitle(), groupSub.getUsers().size())), 
                result.getGroupStatDTOs());
    }
    
}
