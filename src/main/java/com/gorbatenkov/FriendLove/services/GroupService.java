package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Group;
import com.gorbatenkov.FriendLove.repositories.GroupsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupService {
    private final GroupsRepository groupsRepository;

    public GroupService(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    public Optional<Group> findById(int id) {
        return groupsRepository.findById(id);
    }

    public List<Group> findByNameContaining(String name) {
        return groupsRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public int save(Group group) {
        group.setCreatedAt(LocalDateTime.now());
        group.setAvatar("");

        return groupsRepository.save(group).getId();
    }

    @Transactional
    public void update(Group group) {
        groupsRepository.save(group);
    }

}
