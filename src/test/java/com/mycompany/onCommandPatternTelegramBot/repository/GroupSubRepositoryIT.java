package com.mycompany.onCommandPatternTelegramBot.repository;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 * Integration-level testing for {@link GroupSubRepository}
 */

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GroupSubRepositoryIT {
   
    @Autowired
   private GroupSubRepository groupSubRepository;

    /**
     * Test of Properly Get All Users For GroupSub method, of class GroupSubRepository.
     */
    @Test
    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/fiveUsersForGroupSub.sql"})
    public void testProperlyGetAllUsersForGroupSub() {
        System.out.println("testProperlyGetAllUsersForGroupSub");
        // when
        Optional<GroupSub> groupSubFromDB = groupSubRepository.findById(1);
        // then
        assertTrue(groupSubFromDB.isPresent());
        assertEquals(1, groupSubFromDB.get().getId());
        
        List<TelegaUser> users = groupSubFromDB.get().getUsers();
        
        for (int i = 0; i < users.size(); i++) {
            assertEquals(String.valueOf(i+1), users.get(i).getChatId());
            assertTrue(users.get(i).isActives());
        }
    }    
}
