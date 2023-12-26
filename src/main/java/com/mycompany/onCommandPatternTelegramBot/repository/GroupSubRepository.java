package com.mycompany.onCommandPatternTelegramBot.repository;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * {@link Repository} for handling {@link GroupSub} entity.
 */
@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
   
}
