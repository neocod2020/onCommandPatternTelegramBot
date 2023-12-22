package com.mycompany.onCommandPatternTelegramBot.jRClient;

import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupCountRequestArgs;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupDiscussionInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupInfo;
import static com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupInfoType.TECH;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupRequestArgs;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


@DisplayName("Integration-level testing for JrGroupClient")
public class JrGroupClientImplTest {

    private final JrGroupClient groupClient = new JrGroupClientImpl("https://javarush.com/api/1.0/rest");

    /**
     * Test of getGroupList method, of class JrGroupClientImpl.
     */
    @Test
    public void testGetGroupListWithEmptyArgs() {
        System.out.println("testGetGroupListWithEmptyArgs");
        //given
        GroupRequestArgs requestArgs = GroupRequestArgs.builder().build();
        //when        
        List<GroupInfo> result = groupClient.getGroupList(requestArgs);
        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getGroupList method, of class JrGroupClientImpl.
     */
    @Test
    public void testGetGroupDiscussionListWithEmptyArgs() {
        System.out.println("testGetGroupDiscussionListWithEmptyArgs");
        //given
        GroupRequestArgs requestArgs = GroupRequestArgs.builder().build();
        //when        
        List<GroupDiscussionInfo> result = groupClient.getGroupDiscussionList(requestArgs);
        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getGroupDiscussionList method, of class JrGroupClientImpl.
     */
    @Test
    public void testProperlyGetGroupListWithOffsetAndLimit() {
        System.out.println("testProperlyGetGroupListWithOffsetAndLimit");
        //given
        GroupRequestArgs requestArgs = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();
        //when
        List<GroupInfo> groupList = groupClient.getGroupList(requestArgs);
        //then
        assertNotNull(groupList);
        assertEquals(3, groupList.size());
    }

    /**
     * Test of getGroupDiscussionList method, of class JrGroupClientImpl.
     */
    @Test
    public void testProperlyGetGroupDiscussionListWithOffsetAndLimit() {
        System.out.println("testProperlyGetGroupDiscussionListWithOffsetAndLimit");
        //given
        GroupRequestArgs requestArgs = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();
        //when
        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(requestArgs);
        //then
        assertNotNull(groupList);
        assertEquals(3, groupList.size());
    }

    /**
     * Test of getGroupCount method, of class JrGroupClientImpl.
     */
    @Test
    public void testShouldProperlyGetGroupCount() {
        System.out.println("testShouldProperlyGetGroupCount");
        // given
        GroupCountRequestArgs countRequestArgs = GroupCountRequestArgs.builder().build();
        //when        
        Integer groupCount = groupClient.getGroupCount(countRequestArgs);
        // then
        assertEquals(26, groupCount);
    }

    /**
     * Test of getGroupCount method, of class JrGroupClientImpl.
     */
    @Test
    public void testShouldProperlyGetGroupTECHCount() {
        System.out.println("testShouldProperlyGetGroupTECHCount");
        // given
        GroupCountRequestArgs countRequestArgs = GroupCountRequestArgs.builder()
                .type(TECH)
                .build();
        //when        
        Integer groupCount = groupClient.getGroupCount(countRequestArgs);
        // then
        assertEquals(7, groupCount);
    }

    /**
     * Test of getGroupById method, of class JrGroupClientImpl.
     */
    @Test
    public void testProperlyGetGroupById() {
        System.out.println("testProperlyGetGroupById");
        //given
        Integer androidGroupId = 16;
        //when
        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        //then
        assertNotNull(groupById);
        assertEquals(16, groupById.getId());
        assertEquals(TECH, groupById.getType());
        assertEquals("android", groupById.getKey());
    }

}
