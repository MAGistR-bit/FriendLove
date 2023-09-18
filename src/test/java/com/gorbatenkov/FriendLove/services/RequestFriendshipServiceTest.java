package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.RequestFriendship;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.RequestsFriendshipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestFriendshipServiceTest {

    @Mock
    private RequestsFriendshipRepository requestsFriendshipRepository;

    @InjectMocks
    private RequestFriendshipService requestFriendshipService;

    public RequestFriendshipServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        RequestFriendship requestFriendship = new RequestFriendship();
        requestFriendshipService.save(requestFriendship);
        verify(requestsFriendshipRepository, times(1)).save(requestFriendship);
    }

    @Test
    void testFindBySender() {
        User sender = new User();
        List<User> expectedReceiversList = new ArrayList<>();
        when(requestsFriendshipRepository.findBySender(sender)).thenReturn(new ArrayList<>());

        List<User> result = requestFriendshipService.findBySender(sender);

        assertEquals(expectedReceiversList, result);
    }

    @Test
    void testFindByReceiver() {
        User receiver = new User();
        List<User> expectedSendersList = new ArrayList<>();
        when(requestsFriendshipRepository.findByReceiver(receiver)).thenReturn(new ArrayList<>());

        List<User> result = requestFriendshipService.findByReceiver(receiver);

        assertEquals(expectedSendersList, result);
    }

    @Test
    void testFindBySenderAndReceiver() {
        User sender = new User();
        User receiver = new User();
        Optional<RequestFriendship> expectedRequest = Optional.of(new RequestFriendship());
        when(requestsFriendshipRepository.findBySenderAndReceiver(sender, receiver)).thenReturn(expectedRequest);

        Optional<RequestFriendship> result = requestFriendshipService.findBySenderAndReceiver(sender, receiver);

        assertEquals(expectedRequest, result);
    }

    @Test
    void testDeleteBySenderAndReceiver() {
        User sender = new User();
        User receiver = new User();
        requestFriendshipService.deleteBySenderAndReceiver(sender, receiver);
        verify(requestsFriendshipRepository, times(1)).deleteBySenderAndReceiver(sender, receiver);
    }
}