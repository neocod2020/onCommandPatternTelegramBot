package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupDiscussionInfo;

/**
 *
 * Service for manipulating {@link GroupSub}.
 */
public interface GroupSubService {
    
    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
    
    
}
