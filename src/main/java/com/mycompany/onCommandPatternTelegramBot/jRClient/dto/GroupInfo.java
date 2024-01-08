package com.mycompany.onCommandPatternTelegramBot.jRClient.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 * Group information related to autorized user. If there is no user - will be null.
 */

@Data
@ToString
public class GroupInfo {
    private MeGroupInfo meGroupInfo;
    private Integer id;
    private String avatarUrl;
    private String createTime;
    private String description;
    private String key;
    private Integer levelToEditor;
    private String pictureUrl;
    private String title;
    private Integer userCount;
    private GroupInfoType type;
    private GroupVisibilityStatus visibilityStatus;
}
