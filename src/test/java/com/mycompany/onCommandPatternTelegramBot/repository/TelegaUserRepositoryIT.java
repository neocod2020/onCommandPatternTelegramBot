package com.mycompany.onCommandPatternTelegramBot.repository;

import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * Integration-level testing for {@link TelegaUserRepository}
 */

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TelegaUserRepositoryIT {    
    
    @Autowired
    private TelegaUserRepository telegaUserRepository;

    /**
     * Test of findAllByActivesTrue method, of class TelegaUserRepository.
     */
    @Test
    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    public void testFindAllByActivesTrue() {
        System.out.println("testFindAllByActivesTrue");
        //when
        List<TelegaUser> result = telegaUserRepository.findAllByActivesTrue();
        //then
        assertEquals(5, result.size());        
    }

    @Test
    @Sql(scripts = {"/sql/clearDbs.sql"})
    public void testProperlySaveTelegaUser() {
        System.out.println("testProperlySaveTelegaUser");
        //given
        TelegaUser telegaUser = new TelegaUser();
        telegaUser.setChatId("1234567890");
        telegaUser.setActives(false);
        telegaUserRepository.save(telegaUser);
        //when
        Optional<TelegaUser> saved = telegaUserRepository.findById("1234567890");
        //then
        assertTrue(saved.isPresent());
        assertEquals(telegaUser, saved.get());        
    }
    
}
