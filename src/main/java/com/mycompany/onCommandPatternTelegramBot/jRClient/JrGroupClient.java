package com.mycompany.onCommandPatternTelegramBot.jRClient;

import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupCountRequestArgs;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupDiscussionInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupRequestArgs;
import java.util.List;

/**
 * Client for Javarush Open API corresponds to Groups
 */
public interface JrGroupClient {
     /**
     * Get all the {@link GroupInfo} filtered by provided {@link GroupRequestArgs}
     * 
     * @param requestArgs provided {@link GroupRequestArgs}
     * @return the collection of the {@link GroupInfo} objects.
     */   
    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);
    
    /**
     * Get all the {@link GroupDiscussionInfo} filtered by provided {@link GroupRequestArgs}
     * 
     * @param requestArgs provided {@link GroupRequestArgs}
     * @return the collection of the {@link GroupDiscussionInfo} objects.
     */
    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);
    
    /**
     * Get count of groups filtered by provided {@link GroupRequestArgs}
     * 
     * @param countRequestArgs provided {@link GroupsCountRequestArgs}
     * @return the count of groups.
     */   
    Integer getGroupCount(GroupCountRequestArgs countRequestArgs);
    
    /**
     * Get {@link GroupDiscussionInfo} by provided ID.
     * 
     * @param id provided ID.
     * @return {@link GroupDiscussionInfo} object.
     */
    GroupDiscussionInfo getGroupById(Integer id);
}
