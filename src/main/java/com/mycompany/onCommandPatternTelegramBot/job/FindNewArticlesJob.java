package com.mycompany.onCommandPatternTelegramBot.job;

import com.mycompany.onCommandPatternTelegramBot.service.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindNewArticlesJob {    

    private final FindNewArticleService findNewArticleService;    

    @Autowired
    public FindNewArticlesJob(FindNewArticleService findNewArticleService) {
        this.findNewArticleService = findNewArticleService;
    }
   
    @Scheduled(fixedRateString = "${bot.recountNewArticlesFixedRate}")
    public void findNewArticles() {
        LocalDateTime startTime = LocalDateTime.now();        
        log.info("findNewArticles started");
        findNewArticleService.findNewArticles();
        LocalDateTime endTime = LocalDateTime.now();        
        log.info("findNewArticles job finished. Took seconds: {}",
                endTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC));
                
        
    }

}
