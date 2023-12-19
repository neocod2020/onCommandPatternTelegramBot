package com.mycompany.onCommandPatternTelegramBot.jRClient.dto;

import lombok.Data;

/**
 *
 * Group information related to autorized user. If there is no user - will be null.
 */

@Data
public class UserDiscussionInfo {
    private Boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;
}
