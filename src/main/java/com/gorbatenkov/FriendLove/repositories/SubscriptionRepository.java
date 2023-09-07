package com.gorbatenkov.FriendLove.repositories;

import com.gorbatenkov.FriendLove.models.SubscriptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionGroup,Integer> {
}
