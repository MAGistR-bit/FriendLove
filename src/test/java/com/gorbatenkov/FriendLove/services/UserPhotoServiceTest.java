package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.UserPhoto;
import com.gorbatenkov.FriendLove.repositories.UserPhotosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPhotoServiceTest {

    @Mock
    private UserPhotosRepository userPhotosRepository;

    @InjectMocks
    private UserPhotoService userPhotoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        int photoId = 1;
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(photoId);

        when(userPhotosRepository.findById(photoId)).thenReturn(Optional.of(userPhoto));

        Optional<UserPhoto> foundPhoto = userPhotoService.findById(photoId);

        assertTrue(foundPhoto.isPresent());
        assertEquals(photoId, foundPhoto.get().getId());
    }

    @Test
    void testFindByPath() {
        String photoPath = "/path/to/photo.jpg";
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setPhotoPath(photoPath);

        when(userPhotosRepository.findByPhotoPath(photoPath)).thenReturn(Optional.of(userPhoto));

        Optional<UserPhoto> foundPhoto = userPhotoService.findByPath(photoPath);

        assertTrue(foundPhoto.isPresent());
        assertEquals(photoPath, foundPhoto.get().getPhotoPath());
    }

    @Test
    void testSave() {
        UserPhoto userPhoto = new UserPhoto();

        userPhotoService.save(userPhoto);

        verify(userPhotosRepository, times(1)).save(userPhoto);
    }

    @Test
    void testUpdate() {
        UserPhoto userPhoto = new UserPhoto();

        userPhotoService.update(userPhoto);

        verify(userPhotosRepository, times(1)).save(userPhoto);
    }

    @Test
    void testDelete() {
        int photoId = 1;

        userPhotoService.delete(photoId);

        verify(userPhotosRepository, times(1)).deleteById(photoId);
    }

    @Test
    void testGetLastId() {
        int lastId = 10;

        when(userPhotosRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(
                new UserPhoto(lastId, LocalDateTime.now(),
                        "/path/to/photo.jpg", 5)));

        assertEquals(lastId, userPhotoService.getLastId());
    }
}
