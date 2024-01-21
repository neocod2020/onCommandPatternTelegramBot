package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.repository.TelegaUserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegaUserServiceImpl implements TelegaUserService {
    
    @Autowired
    TelegaUserRepository telegaUserRepository;

    
    public TelegaUserServiceImpl(TelegaUserRepository telegaUserRepository) {
        this.telegaUserRepository = telegaUserRepository;
    }

    @Override
    public void save(TelegaUser telegaUser) {
        telegaUserRepository.save(telegaUser);
    }

    @Override
    public List<TelegaUser> retrieveAllActivesUsers() {
        return telegaUserRepository.findAllByActivesTrue();
    }

    @Override
    public Optional<TelegaUser> findByChatId(String chatId) {
        return telegaUserRepository.findById(chatId);
    }

    @Override
    public List<TelegaUser> findAllActivesUsers() {
        return telegaUserRepository.findAllByActivesTrue();
    }

    @Override
    public List<TelegaUser> findAllInActivesUsers() {
        return telegaUserRepository.findAllByActivesFalse();
    }
}
