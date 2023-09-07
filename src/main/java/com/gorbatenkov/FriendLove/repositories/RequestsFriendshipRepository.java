package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.RequestFriendship;
import com.gorbatenkov.FriendLove.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestsFriendshipRepository extends JpaRepository<RequestFriendship,Integer> {
    List<RequestFriendship> findBySender(User sender);
    List<RequestFriendship> findByReceiver(User receiver);

    void deleteBySenderAndReceiver(User sender,User receiver);
    Optional<RequestFriendship> findBySenderAndReceiver(User sender,User receiver);
}
