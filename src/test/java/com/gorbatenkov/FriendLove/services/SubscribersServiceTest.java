package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Subscriber;
import com.gorbatenkov.FriendLove.repositories.SubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class SubscribersServiceTest {

    @Mock
    private SubscriberRepository subscriberRepository;

    @InjectMocks
    private SubscribersService subscribersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveSubscriber() {
        // Create a sample Subscriber
        Subscriber subscriber = new Subscriber();
        // Mock the repositories save method
        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);

        // Call the services save method
        subscribersService.save(subscriber);

        // Verify that the repositories save method was called once with the subscriber
        verify(subscriberRepository, times(1)).save(subscriber);
    }
}