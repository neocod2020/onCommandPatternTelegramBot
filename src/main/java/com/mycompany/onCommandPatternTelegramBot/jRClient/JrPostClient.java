package com.mycompany.onCommandPatternTelegramBot.jRClient;

import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_post.PostInfo;
import java.util.List;

/**
 * Client for Javarush Open API corresponds to Posts
 */
public interface JrPostClient {

    /**
     * Find new posts since lastPostId in provided group
     *
     * @param groupId provided group ID.
     * @param lastPostId provided last post ID.
     * @return the collection of the new {@link PostInfo}.
     */
    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);

}
