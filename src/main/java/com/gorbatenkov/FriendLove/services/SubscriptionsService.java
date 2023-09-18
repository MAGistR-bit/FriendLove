package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.SubscriptionGroup;
import com.gorbatenkov.FriendLove.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SubscriptionsService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionsService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public void save(SubscriptionGroup subscriptionGroup) {
        subscriptionRepository.save(subscriptionGroup);
    }


}
