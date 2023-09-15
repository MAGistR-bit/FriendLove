package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupPostComment;
import com.gorbatenkov.FriendLove.repositories.GroupPostCommentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Использую Mockito для создания mock объекта GroupPostCommentsRepository
 * и проверки вызова соответствующих методов.
 * Также, проверяю возвращаемые значения метода findById через Optional.
 */
public class GroupPostCommentServiceTest {

    @Mock
    private GroupPostCommentsRepository groupPostCommentsRepository;

    private GroupPostCommentService groupPostCommentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        groupPostCommentService = new GroupPostCommentService(groupPostCommentsRepository);
    }

    @Test
    public void findByIdGroupPostComment_shouldReturnOptional() {
        int id = 123;
        GroupPostComment groupPostComment = new GroupPostComment();
        when(groupPostCommentsRepository.findById(id)).thenReturn(Optional.of(groupPostComment));

        Optional<GroupPostComment> foundGroupPostComment = groupPostCommentService.findById(id);

        assertTrue(foundGroupPostComment.isPresent());
        assertEquals(groupPostComment, foundGroupPostComment.get());
    }

    @Test
    public void findByIdGroupPostComment_shouldReturnEmptyOptional() {
        int id = 123;
        when(groupPostCommentsRepository.findById(id)).thenReturn(Optional.empty());

        Optional<GroupPostComment> foundGroupPostComment = groupPostCommentService.findById(id);

        assertFalse(foundGroupPostComment.isPresent());
    }

    @Test
    public void saveGroupPostComment_shouldCallRepositorySave() {
        GroupPostComment groupPostComment = new GroupPostComment();
        groupPostCommentService.save(groupPostComment);
        verify(groupPostCommentsRepository).save(groupPostComment);
    }

    @Test
    public void updateGroupPostComment_shouldCallRepositorySave() {
        GroupPostComment groupPostComment = new GroupPostComment();
        groupPostCommentService.update(groupPostComment);
        verify(groupPostCommentsRepository).save(groupPostComment);
    }

    @Test
    public void deleteGroupPostComment_shouldCallRepositoryDeleteById() {
        int id = 123;
        groupPostCommentService.delete(id);
        verify(groupPostCommentsRepository).deleteById(id);
    }
}