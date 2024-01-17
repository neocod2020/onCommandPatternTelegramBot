package com.mycompany.onCommandPatternTelegramBot.jRClient;

import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_group_subscription.GroupCountRequestArgs;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_group_subscription.GroupDiscussionInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_group_subscription.GroupInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_group_subscription.GroupRequestArgs;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_post.PostInfo;
import java.util.List;
import java.util.Optional;
import kong.unirest.GenericType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import kong.unirest.Unirest;

/**
 * Implementation of the {@link JrGroupClient} interface.
 */
@Component
public class JrGroupClientImpl implements JrGroupClient {

    private final String jrApiGroupPath;
    private final String jrApiPostPath;

    public JrGroupClientImpl(@Value("${jr.api.path}") String jrApi) {
        this.jrApiGroupPath = jrApi + "/groups";
        this.jrApiPostPath = jrApi + "/posts";
    }

    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(jrApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                })
                .getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
        return Unirest.get(jrApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                })
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupCountRequestArgs countRequestArgs) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", jrApiGroupPath))
                        .queryString(countRequestArgs.populateQueries())
                        .asString()
                        .getBody()
        );
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", jrApiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }

    @Override
    public Integer findLastPostId(Integer groupSubId) {
        
        List<PostInfo> posts = Unirest.get(jrApiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupSubId.toString())
                .queryString("limit", "1")
                .asObject(new GenericType<List<PostInfo>>(){
                })
                .getBody();
        return posts.isEmpty() ? 0 : Optional.ofNullable(posts.get(0)).map(PostInfo::getId).orElse(0);
    }

}
