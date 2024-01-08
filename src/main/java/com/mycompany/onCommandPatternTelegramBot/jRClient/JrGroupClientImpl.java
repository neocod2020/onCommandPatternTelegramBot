package com.mycompany.onCommandPatternTelegramBot.jRClient;

import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupCountRequestArgs;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupDiscussionInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupRequestArgs;
import java.util.List;
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

    public JrGroupClientImpl(@Value("${jr.api.path}") String jrApi) {
        this.jrApiGroupPath = jrApi + "/groups";
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

}
