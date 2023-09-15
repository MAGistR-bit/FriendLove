package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupPostComment;
import com.gorbatenkov.FriendLove.repositories.GroupPostCommentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupPostCommentService {
    private final GroupPostCommentsRepository groupPostCommentsRepository;

    public GroupPostCommentService(GroupPostCommentsRepository groupPostCommentsRepository) {
        this.groupPostCommentsRepository = groupPostCommentsRepository;
    }

    @Transactional
    public void save(GroupPostComment groupPostComment) {
        groupPostCommentsRepository.save(groupPostComment);
    }

    @Transactional
    public void update(GroupPostComment groupPostComment) {
        groupPostCommentsRepository.save(groupPostComment);
    }

    @Transactional
    public void delete(int id) {
        groupPostCommentsRepository.deleteById(id);
    }

    public Optional<GroupPostComment> findById(int id) {
        return groupPostCommentsRepository.findById(id);
    }
}
