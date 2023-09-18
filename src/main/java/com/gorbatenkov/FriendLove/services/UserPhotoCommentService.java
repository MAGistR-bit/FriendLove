package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.UserPhotoComment;
import com.gorbatenkov.FriendLove.repositories.UserPhotoCommentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserPhotoCommentService {
    private final UserPhotoCommentsRepository userPhotoCommentsRepository;

    public UserPhotoCommentService(UserPhotoCommentsRepository userPhotoCommentsRepository) {
        this.userPhotoCommentsRepository = userPhotoCommentsRepository;
    }

    @Transactional
    public void save(UserPhotoComment userPhotoComment) {
        userPhotoCommentsRepository.save(userPhotoComment);
    }

    @Transactional
    public void update(UserPhotoComment userPhotoComment) {
        userPhotoCommentsRepository.save(userPhotoComment);
    }

    @Transactional
    public void delete(int id) {
        userPhotoCommentsRepository.deleteById(id);
    }

    public Optional<UserPhotoComment> findById(int id) {
        return userPhotoCommentsRepository.findById(id);
    }
}
