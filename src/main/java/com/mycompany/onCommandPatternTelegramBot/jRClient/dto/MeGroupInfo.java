package com.mycompany.onCommandPatternTelegramBot.jRClient.dto;

import lombok.Data;

/**
 *
 * Group information related to autorized user. If there is no user - will be null.
 */

@Data
public class MeGroupInfo {
    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
