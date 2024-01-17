package com.mycompany.onCommandPatternTelegramBot.service;

/**
 * Service for finding new articles.
 */
public interface FindNewArticleService {

    /**
     * Finding new articles and notify subscribers about it.
     */
    void findNewArticles();
}
