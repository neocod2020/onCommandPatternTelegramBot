package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.STATISTIC;
import static com.mycompany.onCommandPatternTelegramBot.command.StatistCommand.STATISTIC_MESSAGE;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic.GroupStatDTO;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic.StatisticDTO;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.StatisticsService;
import java.util.Collections;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;


public class StatistCommandTest  {

    private SendBotMessageService sendBotMessageService;    
    private StatisticsService statisticsService;
    private Command statCommand;

    @BeforeEach
    public void setUp(){
        sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        statisticsService = Mockito.mock(StatisticsService.class);
        statCommand = new StatistCommand(sendBotMessageService, statisticsService);
    }   
    
    @Test
    public void testProperlySendMessage() {
        System.out.println("StatistCommandTest. testProperlySendMessage");
        // given
        Long chatId = 1234567L;
        GroupStatDTO groupStatDTO = new GroupStatDTO(1, "group", 1);
        StatisticDTO statisticDTO = new StatisticDTO(1, 1, Collections.singletonList(groupStatDTO), 2.5);
        Mockito.when(statisticsService.countBotStatistic()).thenReturn(statisticDTO);
        
        // when
        statCommand.execute(AbstractCommandTest.prepareUpdate(chatId, CommandName.STATISTIC.getCommandName()));
        
        // then
        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), String.format(STATISTIC_MESSAGE, 
                statisticDTO.getActiveUserCount(), statisticDTO.getInactiveUserCount(), 
                statisticDTO.getAverageGroupCountByUser(), 
                String.format("%s (id = %s) - %s subscribers", groupStatDTO.getTitle(), 
                        groupStatDTO.getId(), groupStatDTO.getActiveUserCount())));
        
    } 
    
    
}
