package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Subscriber;
import com.gorbatenkov.FriendLove.repositories.SubscriberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SubscribersService {
    private final SubscriberRepository subscriberRepository;

    public SubscribersService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Transactional
    public void save(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }
}
