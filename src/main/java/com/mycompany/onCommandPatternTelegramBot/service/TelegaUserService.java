package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import java.util.List;
import java.util.Optional;

/**
 *
 * {@link Service} for handling {@link TelegaUser} entity.
 */
public interface TelegaUserService {
    
    void save(TelegaUser telegaUser);
    List<TelegaUser> retrieveAllActivesUsers();
    Optional<TelegaUser> findByChatId(String chatId);
    
}
