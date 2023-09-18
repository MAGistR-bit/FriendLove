package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.UserPhoto;
import com.gorbatenkov.FriendLove.repositories.UserPhotosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserPhotoService {
    private final UserPhotosRepository userPhotosRepository;

    public UserPhotoService(UserPhotosRepository userPhotosRepository) {
        this.userPhotosRepository = userPhotosRepository;
    }

    public Optional<UserPhoto> findById(int id) {
        return userPhotosRepository.findById(id);
    }

    public Optional<UserPhoto> findByPath(String path) {
        return userPhotosRepository.findByPhotoPath(path);
    }

    @Transactional
    public void save(UserPhoto userPhoto) {
        userPhotosRepository.save(userPhoto);
    }

    @Transactional
    public void update(UserPhoto userPhoto) {
        userPhotosRepository.save(userPhoto);
    }

    @Transactional
    public void delete(int id) {
        userPhotosRepository.deleteById(id);
    }

    public int getLastId() {
        Optional<UserPhoto> userPhoto = userPhotosRepository.findTopByOrderByIdDesc();
        return userPhoto.map(UserPhoto::getId).orElse(-1);
    }
}
