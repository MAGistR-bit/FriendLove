package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.SubscriptionGroup;
import com.gorbatenkov.FriendLove.repositories.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class SubscriptionsServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionsService subscriptionsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveSubscriptionGroup() {
        // Create a sample SubscriptionGroup
        SubscriptionGroup subscriptionGroup = new SubscriptionGroup();

        // Mock the repositories save method
        when(subscriptionRepository.save(subscriptionGroup)).thenReturn(subscriptionGroup);

        // Call the services save method
        subscriptionsService.save(subscriptionGroup);

        // Verify that the repositories save method was called once with the subscriptionGroup
        verify(subscriptionRepository, times(1)).save(subscriptionGroup);
    }
}