package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic.StatisticDTO;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic.GroupStatDTO;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final GroupSubService groupSubService;
    private final TelegaUserService telegaUserService;

    public StatisticsServiceImpl(GroupSubService groupSubService, TelegaUserService telegaUserService) {
        this.groupSubService = groupSubService;
        this.telegaUserService = telegaUserService;
    }

    @Override
    public StatisticDTO countBotStatistic() {
        List<GroupStatDTO> groupStatDTOs = groupSubService.findAll().stream()
                .filter(it -> !it.getUsers().isEmpty())
                .map(groupSub -> new GroupStatDTO(groupSub.getId(), groupSub.getTitle(), groupSub.getUsers().size()))
                .collect(Collectors.toList());
        List<TelegaUser> allActiveUsers = telegaUserService.findAllActivesUsers();
        List<TelegaUser> allInActiveUsers = telegaUserService.findAllInActivesUsers();

        double groupsPerUser = getGroupsPerUser(allActiveUsers);
        return new StatisticDTO(allActiveUsers.size(), allInActiveUsers.size(), groupStatDTOs, groupsPerUser);
    }

    private double getGroupsPerUser(List<TelegaUser> allActiveUsers) {
        return (double) allActiveUsers.stream()
                .mapToInt(it -> it.getGroupSubs().size()).sum() / allActiveUsers.size();
    }

}
