package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.Message;
import com.gorbatenkov.FriendLove.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessagesRepository extends JpaRepository<Message,Integer> {
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
    List<Message> findBySenderAndReceiver(User sender,User receiver);
    Optional<Message> findTopBySenderAndReceiverOrderByCreatedAtDesc(User sender, User receiver);
}
