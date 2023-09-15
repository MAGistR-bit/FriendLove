package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupPost;
import com.gorbatenkov.FriendLove.repositories.GroupPostsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Эти тесты проверяют различные методы в классе GroupPostService и
 * используют Mockito для создания mock объекта GroupPostsRepository.
 */
public class GroupPostServiceTest {

    @Mock
    private GroupPostsRepository groupPostsRepository;

    private GroupPostService groupPostService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        groupPostService = new GroupPostService(groupPostsRepository);
    }

    @Test
    public void findByIdGroupPost_shouldReturnOptional() {
        int id = 123;
        GroupPost groupPost = new GroupPost();
        when(groupPostsRepository.findById(id)).thenReturn(Optional.of(groupPost));

        Optional<GroupPost> foundGroupPost = groupPostService.findById(id);

        assertTrue(foundGroupPost.isPresent());
        assertEquals(groupPost, foundGroupPost.get());
    }

    @Test
    public void findByIdGroupPost_shouldReturnEmptyOptional() {
        int id = 123;
        when(groupPostsRepository.findById(id)).thenReturn(Optional.empty());

        Optional<GroupPost> foundGroupPost = groupPostService.findById(id);

        assertFalse(foundGroupPost.isPresent());
    }

    @Test
    public void saveGroupPost_shouldSetRatingAndCreatedAtThenCallRepositorySave() {
        GroupPost groupPost = new GroupPost();
        groupPostService.save(groupPost);

        // Verify that rating is set to 0 and createdAt is set to a non-null value
        assertEquals(0, groupPost.getRating());
        assertNotNull(groupPost.getCreatedAt());

        verify(groupPostsRepository).save(groupPost);
    }

    @Test
    public void updateGroupPost_shouldCallRepositorySave() {
        GroupPost groupPost = new GroupPost();
        groupPostService.update(groupPost);
        verify(groupPostsRepository).save(groupPost);
    }

    @Test
    public void deleteByIdGroupPost_shouldCallRepositoryDeleteById() {
        int id = 123;
        groupPostService.deleteById(id);
        verify(groupPostsRepository).deleteById(id);
    }
}