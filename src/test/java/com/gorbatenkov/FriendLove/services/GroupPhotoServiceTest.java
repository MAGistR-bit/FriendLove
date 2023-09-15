package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupPhoto;
import com.gorbatenkov.FriendLove.repositories.GroupPhotosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GroupPhotoServiceTest {

    @Mock
    private GroupPhotosRepository groupPhotosRepository;

    private GroupPhotoService groupPhotoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        groupPhotoService = new GroupPhotoService(groupPhotosRepository);
    }

    @Test
    public void findByIdGroupPhoto_shouldReturnOptional() {
        int id = 123;
        GroupPhoto groupPhoto = new GroupPhoto();
        when(groupPhotosRepository.findById(id)).thenReturn(Optional.of(groupPhoto));

        Optional<GroupPhoto> foundGroupPhoto = groupPhotoService.findById(id);

        assertTrue(foundGroupPhoto.isPresent());
        assertEquals(groupPhoto, foundGroupPhoto.get());
    }

    @Test
    public void findByIdGroupPhoto_shouldReturnEmptyOptional() {
        int id = 123;
        when(groupPhotosRepository.findById(id)).thenReturn(Optional.empty());

        Optional<GroupPhoto> foundGroupPhoto = groupPhotoService.findById(id);

        assertFalse(foundGroupPhoto.isPresent());
    }

    @Test
    public void findByPathGroupPhoto_shouldReturnOptional() {
        String path = "path/to/photo";
        GroupPhoto groupPhoto = new GroupPhoto();
        when(groupPhotosRepository.findByPhotoPath(path)).thenReturn(Optional.of(groupPhoto));

        Optional<GroupPhoto> foundGroupPhoto = groupPhotoService.findByPath(path);

        assertTrue(foundGroupPhoto.isPresent());
        assertEquals(groupPhoto, foundGroupPhoto.get());
    }

    @Test
    public void findByPathGroupPhoto_shouldReturnEmptyOptional() {
        String path = "path/to/photo";
        when(groupPhotosRepository.findByPhotoPath(path)).thenReturn(Optional.empty());

        Optional<GroupPhoto> foundGroupPhoto = groupPhotoService.findByPath(path);

        assertFalse(foundGroupPhoto.isPresent());
    }

    @Test
    public void getLastId_shouldReturnLastId() {
        int lastId = 123;
        GroupPhoto groupPhoto = new GroupPhoto();
        groupPhoto.setId(lastId);
        when(groupPhotosRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(groupPhoto));

        int returnedId = groupPhotoService.getLastId();

        assertEquals(lastId, returnedId);
    }

    @Test
    public void getLastId_shouldReturnMinusOneIfNoPhotos() {
        when(groupPhotosRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());
        int returnedId = groupPhotoService.getLastId();
        assertEquals(-1, returnedId);
    }

    @Test
    public void saveGroupPhoto_shouldCallRepositorySave() {
        GroupPhoto groupPhoto = new GroupPhoto();
        groupPhotoService.save(groupPhoto);
        verify(groupPhotosRepository).save(groupPhoto);
    }

    @Test
    public void deleteGroupPhoto_shouldCallRepositoryDeleteById() {
        int id = 123;
        groupPhotoService.delete(id);
        verify(groupPhotosRepository).deleteById(id);
    }
}