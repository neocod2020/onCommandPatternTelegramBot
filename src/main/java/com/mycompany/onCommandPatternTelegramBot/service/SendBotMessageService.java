package com.mycompany.onCommandPatternTelegramBot.service;

import java.util.List;

/**
 *
 * Service for sending messages via telegram bot.
 */
public interface SendBotMessageService {
    
    /**
     * send message via telegram bot.
     * @param chatId provided chatId in which message would be sent.
     * @param message provided message to be sent.
     */
    
    void sendMessage(String chatId, String message);

    public void sendMessage(String chatId, List<String> messages);
}
