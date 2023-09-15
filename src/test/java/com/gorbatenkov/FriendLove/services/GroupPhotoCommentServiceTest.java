package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupPhotoComment;
import com.gorbatenkov.FriendLove.repositories.GroupPhotoCommentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Использую Mockito для создания
 * mock объекта GroupPhotoCommentsRepository и проверки
 * вызова соответствующих методов.
 * Также, проверяю возвращаемые значения метода findById через Optional.
 */
public class GroupPhotoCommentServiceTest {

    @Mock
    private GroupPhotoCommentsRepository groupPhotoCommentsRepository;

    private GroupPhotoCommentService groupPhotoCommentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        groupPhotoCommentService = new GroupPhotoCommentService(groupPhotoCommentsRepository);
    }

    @Test
    public void saveGroupPhotoComment_shouldCallRepositorySave() {
        GroupPhotoComment groupPhotoComment = new GroupPhotoComment();
        groupPhotoCommentService.save(groupPhotoComment);
        verify(groupPhotoCommentsRepository).save(groupPhotoComment);
    }

    @Test
    public void updateGroupPhotoComment_shouldCallRepositorySave() {
        GroupPhotoComment groupPhotoComment = new GroupPhotoComment();
        groupPhotoCommentService.update(groupPhotoComment);
        verify(groupPhotoCommentsRepository).save(groupPhotoComment);
    }

    @Test
    public void deleteGroupPhotoComment_shouldCallRepositoryDeleteById() {
        int id = 123;
        groupPhotoCommentService.delete(id);
        verify(groupPhotoCommentsRepository).deleteById(id);
    }

    @Test
    public void findByIdGroupPhotoComment_shouldReturnOptional() {
        int id = 123;
        GroupPhotoComment groupPhotoComment = new GroupPhotoComment();
        when(groupPhotoCommentsRepository.findById(id)).thenReturn(Optional.of(groupPhotoComment));

        Optional<GroupPhotoComment> foundGroupPhotoComment = groupPhotoCommentService.findById(id);

        assertTrue(foundGroupPhotoComment.isPresent());
        assertEquals(groupPhotoComment, foundGroupPhotoComment.get());
    }

    @Test
    public void findByIdGroupPhotoComment_shouldReturnEmptyOptional() {
        int id = 123;
        when(groupPhotoCommentsRepository.findById(id)).thenReturn(Optional.empty());

        Optional<GroupPhotoComment> foundGroupPhotoComment = groupPhotoCommentService.findById(id);

        assertFalse(foundGroupPhotoComment.isPresent());
    }
}