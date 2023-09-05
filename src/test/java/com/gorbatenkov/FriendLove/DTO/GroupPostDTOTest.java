package com.gorbatenkov.FriendLove.DTO;

import com.gorbatenkov.FriendLove.models.Group;
import com.gorbatenkov.FriendLove.models.GroupPostComment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class GroupPostDTOTest {

    private GroupPostDTO groupPostDTO;

    @BeforeEach
    public void setUp() {
        LocalDateTime createdAt = LocalDateTime.now();
        String post = "Test Post";
        int rating = 5;
        Group group = mock(Group.class);
        List<GroupPostComment> groupPostComments = new ArrayList<>();
        Set<Integer> groupContactsId = Set.of(1, 2, 3);

        groupPostDTO = new GroupPostDTO(createdAt, post, rating, group, groupPostComments);
        groupPostDTO.setGroupContactsId(groupContactsId);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(LocalDateTime.now().getYear(), groupPostDTO.getCreatedAt().getYear());
        assertEquals("Test Post", groupPostDTO.getPost());
        assertEquals(5, groupPostDTO.getRating());
        assertNotNull(groupPostDTO.getGroup());
        assertNotNull(groupPostDTO.getGroupPostComments());
        assertNotNull(groupPostDTO.getGroupContactsId());
    }

}