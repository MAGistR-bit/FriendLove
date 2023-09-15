package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupPost;
import com.gorbatenkov.FriendLove.repositories.GroupPostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupPostService {
    private final GroupPostsRepository groupPostsRepository;

    public GroupPostService(GroupPostsRepository groupPostsRepository) {
        this.groupPostsRepository = groupPostsRepository;
    }

    public Optional<GroupPost> findById(int id) {
        return groupPostsRepository.findById(id);
    }

    @Transactional
    public void save(GroupPost groupPost) {
        groupPost.setRating(0);
        groupPost.setCreatedAt(LocalDateTime.now());
        groupPostsRepository.save(groupPost);
    }

    @Transactional
    public void update(GroupPost groupPost) {
        groupPostsRepository.save(groupPost);
    }

    @Transactional
    public void deleteById(int id) {
        groupPostsRepository.deleteById(id);
    }
}
