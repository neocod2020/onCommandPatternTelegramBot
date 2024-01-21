package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.command.annotation.AdminCommand;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic.StatisticDTO;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.StatisticsService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import java.util.stream.Collectors;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * StatistCommand (@link Command)
 */
@AdminCommand
public class StatistCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final StatisticsService statisticsService;

    public final static String STATISTIC_MESSAGE = "<b>Here is this Bot statistics:</b>\n"
            + "active users: %s\n"
            + "non-active users: %s\n"
            + "average quantity groups per user: %s\n\n"
            + "<b>Active groups info:</b>\n"
            + "%s";

    public StatistCommand(SendBotMessageService sendBotMessageService, StatisticsService statisticsService) {
        this.sendBotMessageService = sendBotMessageService;
        this.statisticsService = statisticsService;
    }

    @Override
    public void execute(Update update) {
        StatisticDTO statisticDTO = statisticsService.countBotStatistic();
        String collectedGroups = statisticDTO.getGroupStatDTOs().stream()
                .map(it -> String.format("%s (id = %s) - %s subscribers", it.getTitle(), it.getId(), it.getActiveUserCount()))
                .collect(Collectors.joining("\n"));

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                String.format(STATISTIC_MESSAGE, statisticDTO.getActiveUserCount(), statisticDTO.getInactiveUserCount(),
                        statisticDTO.getAverageGroupCountByUser(), collectedGroups));
    }

}
