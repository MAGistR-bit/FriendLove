package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupContact;
import com.gorbatenkov.FriendLove.repositories.GroupContactsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GroupContactService {
    private final GroupContactsRepository groupContactsRepository;

    public GroupContactService(GroupContactsRepository groupContactsRepository) {
        this.groupContactsRepository = groupContactsRepository;
    }

    @Transactional
    public void save(GroupContact groupContact) {
        groupContactsRepository.save(groupContact);
    }
}
