package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.GroupContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupContactsRepository extends JpaRepository<GroupContact,Integer> {
}
