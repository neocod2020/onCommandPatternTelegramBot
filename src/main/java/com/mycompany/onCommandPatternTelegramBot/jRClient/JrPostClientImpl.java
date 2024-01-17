package com.mycompany.onCommandPatternTelegramBot.jRClient;

import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_post.PostInfo;
import java.util.ArrayList;
import java.util.List;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JrPostClientImpl implements JrPostClient {

    private final String jrApiPostPath;

    public JrPostClientImpl(@Value("${jr.api.path}") String jrApi) {
        log.info("JrPostClientImpl - constructor");
        this.jrApiPostPath = jrApi + "/posts";
    }

    @Override
    public List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId) {
        log.info("findNewPosts");
        List<PostInfo> lastPostsByGroup = Unirest.get(jrApiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupId)
                .queryString("limit", 15)
                .asObject(new GenericType<List<PostInfo>>() {
                })
                .getBody();
        List<PostInfo> newPosts = new ArrayList<>();
        for (PostInfo post : lastPostsByGroup) {
            if (lastPostId.equals(post.getId())) {
                return newPosts;
            }
            newPosts.add(post);
        }
        return newPosts;
    }

}
