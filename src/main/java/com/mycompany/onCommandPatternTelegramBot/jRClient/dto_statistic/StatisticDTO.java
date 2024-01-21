package com.mycompany.onCommandPatternTelegramBot.jRClient.dto_statistic;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for getting bot statistic
 */
@Data
@EqualsAndHashCode
public class StatisticDTO {
    private final int activeUserCount;
    private final int inactiveUserCount;
    private final List<GroupStatDTO> groupStatDTOs;
    private final double averageGroupCountByUser;
}
