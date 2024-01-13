package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.jRClient.JrPostClient;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_post.PostInfo;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindNewArticleServiceImpl implements FindNewArticleService {

    public static final String JAVARUSH_WEB_POST_FORMAT = "https://javarush.com/groups/posts/%s";

    private final String MESSAGE_NEW_ARTICLE = "New article <b>%s</b> is published in the group <b>%s</b>.\n\n"
            + "<b>Description:</b> %s\n\n"
            + "<b>Link to article:</b> %s\n";

    private final GroupSubService groupSubService;
    private final JrPostClient jrPostClient;
    private final SendBotMessageService sendBotMessageService;

    @Autowired
    public FindNewArticleServiceImpl(GroupSubService groupSubService, JrPostClient jrPostClient,
            SendBotMessageService sendBotMessageService) {
        this.groupSubService = groupSubService;
        this.jrPostClient = jrPostClient;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void findNewArticles() {
        groupSubService.findAll() // find all groups
                .forEach(gSub -> {
                    // for each group
                    List<PostInfo> newPosts = jrPostClient.findNewPosts(gSub.getId(), gSub.getLastArticleId());
                    // update id of the last new article
                    setNewLastArticleId(gSub, newPosts);
                    // send to every active user message about new articles
                    notifySubscribersAboutNewArticles(gSub, newPosts);
                });
    }

    private void setNewLastArticleId(GroupSub gSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id -> {
                    gSub.setLastArticleId(id);
                    groupSubService.save(gSub);
                });
    }

    private void notifySubscribersAboutNewArticles(GroupSub gSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messageWithNewPosts = newPosts.stream()
                .map(post -> String.format(MESSAGE_NEW_ARTICLE, post.getTitle(), gSub.getTitle(),
                post.getDescription(), getPostUrl(post.getKey())))
                .collect(Collectors.toList());
        gSub.getUsers().stream()
                .filter(TelegaUser::isActives)
                .forEach(it -> sendBotMessageService.sendMessage(it.getChatId(), messageWithNewPosts));
    }

    private String getPostUrl(String key) {
        return String.format(JAVARUSH_WEB_POST_FORMAT, key);
    }
}
