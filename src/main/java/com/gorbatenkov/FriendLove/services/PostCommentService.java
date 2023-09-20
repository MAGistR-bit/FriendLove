package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.PostComment;
import com.gorbatenkov.FriendLove.repositories.PostCommentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostCommentService {
    private final PostCommentsRepository postCommentsRepository;

    public PostCommentService(PostCommentsRepository postCommentsRepository) {
        this.postCommentsRepository = postCommentsRepository;
    }

    @Transactional
    public void save(PostComment postComment) {
        postComment.setCreatedAt(LocalDateTime.now());
        postCommentsRepository.save(postComment);
    }

    @Transactional
    public void update(PostComment postComment) {
        postCommentsRepository.save(postComment);
    }

    public Optional<PostComment> findById(int id) {
        return postCommentsRepository.findById(id);
    }

    @Transactional
    public void deleteById(int id) {
        postCommentsRepository.deleteById(id);
    }
}
