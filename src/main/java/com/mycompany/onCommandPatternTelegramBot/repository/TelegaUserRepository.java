package com.mycompany.onCommandPatternTelegramBot.repository;

import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * {@link Repository} for handling {@link TelegaUser} entity.
 */
public interface TelegaUserRepository extends JpaRepository<TelegaUser, String> {
    
    List<TelegaUser> findAllByActivesTrue();       

    List<TelegaUser> findAllByActivesFalse();
}
