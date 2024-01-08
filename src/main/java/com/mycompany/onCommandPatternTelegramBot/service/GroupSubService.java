package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupDiscussionInfo;
import java.util.Optional;

/**
 *
 * Service for manipulating {@link GroupSub}.
 */
public interface GroupSubService {
    
    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
    GroupSub save(GroupSub groupSub);
    Optional<GroupSub> findById(Integer groupID);
    
}
