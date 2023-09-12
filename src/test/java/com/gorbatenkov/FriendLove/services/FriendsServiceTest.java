package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Friend;
import com.gorbatenkov.FriendLove.repositories.FriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class FriendsServiceTest {

    private FriendsService friendsService;

    @Mock
    private FriendRepository friendRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        friendsService = new FriendsService(friendRepository);
    }

    @Test
    public void testSaveFriend() {
        Friend friend = new Friend();
        // Mocking the save method of the repository
        when(friendRepository.save(friend)).thenReturn(friend);

        friendsService.save(friend);

        // Verify that the save method of the repository was called once
        verify(friendRepository, times(1)).save(friend);
    }
}