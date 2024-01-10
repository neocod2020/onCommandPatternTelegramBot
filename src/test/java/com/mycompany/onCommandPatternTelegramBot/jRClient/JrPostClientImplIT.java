package com.mycompany.onCommandPatternTelegramBot.jRClient;

import static com.mycompany.onCommandPatternTelegramBot.jRClient.JrGroupClientImplTest.JAVARUSH_API_PATH;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto_post.PostInfo;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


@DisplayName("Integration-level testing for JrPostClientImpl")
public class JrPostClientImplIT {
    
    private final JrPostClient postClient = new JrPostClientImpl(JAVARUSH_API_PATH);   
   
    /**
     * Test of findNewPosts method, of class JrPostClientImpl.
     */
    @Test
    public void testProperlyGetNew15Posts() {
        System.out.println("testProperlyGetNew15Posts");
        Integer groupId = 30;
        Integer lastPostId = 2935;
        
        List<PostInfo> result = postClient.findNewPosts(groupId, lastPostId);
        assertEquals(15, result.size());
        
    }
    
}
