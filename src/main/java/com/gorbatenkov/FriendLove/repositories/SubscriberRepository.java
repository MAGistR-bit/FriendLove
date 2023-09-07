package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber,Integer> {
}
