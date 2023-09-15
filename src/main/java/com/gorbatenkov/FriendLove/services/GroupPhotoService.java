package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupPhoto;
import com.gorbatenkov.FriendLove.repositories.GroupPhotosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupPhotoService {
    private final GroupPhotosRepository groupPhotosRepository;

    public GroupPhotoService(GroupPhotosRepository groupPhotosRepository) {
        this.groupPhotosRepository = groupPhotosRepository;
    }

    public Optional<GroupPhoto> findById(int id) {
        return groupPhotosRepository.findById(id);
    }

    public Optional<GroupPhoto> findByPath(String path) {
        return groupPhotosRepository.findByPhotoPath(path);
    }

    public int getLastId() {
        Optional<GroupPhoto> groupPhoto = groupPhotosRepository.findTopByOrderByIdDesc();
        return groupPhoto.map(GroupPhoto::getId).orElse(-1);
    }

    @Transactional
    public void save(GroupPhoto groupPhoto) {
        groupPhotosRepository.save(groupPhoto);
    }

    @Transactional
    public void delete(int id) {
        groupPhotosRepository.deleteById(id);
    }
}
