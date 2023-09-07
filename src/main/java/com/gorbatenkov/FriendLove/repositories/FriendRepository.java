package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Integer> {
}
