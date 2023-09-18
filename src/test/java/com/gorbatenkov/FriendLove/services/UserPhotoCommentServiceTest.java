package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.UserPhotoComment;
import com.gorbatenkov.FriendLove.repositories.UserPhotoCommentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserPhotoCommentServiceTest {

    @Mock
    private UserPhotoCommentsRepository userPhotoCommentsRepository;

    @InjectMocks
    private UserPhotoCommentService userPhotoCommentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        // Create a sample UserPhotoComment
        UserPhotoComment userPhotoComment = new UserPhotoComment();
        userPhotoComment.setId(1); // Assuming an ID
        userPhotoComment.setComment("This is a comment.");

        // Call the services save method
        userPhotoCommentService.save(userPhotoComment);

        // Verify that the repositories save method was called once
        verify(userPhotoCommentsRepository, times(1)).save(userPhotoComment);
    }

    @Test
    void testUpdate() {
        // Create a sample UserPhotoComment
        UserPhotoComment userPhotoComment = new UserPhotoComment();
        userPhotoComment.setId(1); // Assuming an ID
        userPhotoComment.setComment("Updated comment.");

        // Call the service's update method
        userPhotoCommentService.update(userPhotoComment);

        // Verify that the repositories save method was called once
        verify(userPhotoCommentsRepository, times(1)).save(userPhotoComment);
    }

    @Test
    void testDelete() {
        int commentId = 1; // Assuming an ID

        // Call the service's delete method
        userPhotoCommentService.delete(commentId);

        // Verify that the repository's deleteById method was called once with the correct ID
        verify(userPhotoCommentsRepository, times(1)).deleteById(commentId);
    }

    @Test
    void testFindById() {
        int commentId = 1; // Assuming an ID
        UserPhotoComment userPhotoComment = new UserPhotoComment();
        userPhotoComment.setId(commentId);
        userPhotoComment.setComment("Sample comment");

        // Mock the repository's findById method
        when(userPhotoCommentsRepository.findById(commentId)).thenReturn(Optional.of(userPhotoComment));

        // Call the service's findById method
        Optional<UserPhotoComment> foundComment = userPhotoCommentService.findById(commentId);

        // Verify that the correct UserPhotoComment is returned
        assertTrue(foundComment.isPresent());
        assertEquals(userPhotoComment, foundComment.get());
    }
}