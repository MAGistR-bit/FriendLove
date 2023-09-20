package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Post;
import com.gorbatenkov.FriendLove.repositories.PostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostsRepository postsRepository;

    public PostService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Transactional
    public void save(Post post) {
        post.setRating(0);
        post.setCreatedAt(LocalDateTime.now());
        postsRepository.save(post);
    }

    @Transactional
    public void updated(Post post) {
        postsRepository.save(post);
    }

    @Transactional
    public void deleteById(int id) {
        postsRepository.deleteById(id);
    }

    public Optional<Post> findById(int id) {
        return postsRepository.findById(id);
    }

}
