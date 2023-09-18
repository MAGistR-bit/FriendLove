package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Message;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.MessagesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessagesServiceTest {

    private MessagesService messagesService;

    @Mock
    private MessagesRepository messagesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        messagesService = new MessagesService(messagesRepository);
    }

    @Test
    void saveMessage() {
        Message message = new Message();  // Initialize with appropriate data
        messagesService.save(message);

        verify(messagesRepository, times(1)).save(message);
    }

    @Test
    void testFindBySender() {
        User sender = new User();  // Initialize with appropriate data
        when(messagesRepository.findBySender(sender)).thenReturn(Arrays.asList(new Message(), new Message()));

        List<Message> result = messagesService.findBySender(sender);

        assertEquals(2, result.size());
    }

    @Test
    void testFindByReceiver() {
        User receiver = new User();  // Initialize with appropriate data
        when(messagesRepository.findByReceiver(receiver)).thenReturn(Arrays.asList(new Message(), new Message()));

        List<Message> result = messagesService.findByReceiver(receiver);

        assertEquals(2, result.size());
    }

    @Test
    void testFindBySenderAndReceiver() {
        User sender = new User();  // Initialize with appropriate data
        User receiver = new User();  // Initialize with appropriate data
        when(messagesRepository.findBySenderAndReceiver(sender, receiver)).thenReturn(Arrays.asList(new Message(), new Message()));

        List<Message> result = messagesService.findBySenderAndReceiver(sender, receiver);

        assertEquals(2, result.size());
    }

    @Test
    void testFindBySenderAndReceiverLastMessage() {
        User sender = new User();  // Initialize with appropriate data
        User receiver = new User();  // Initialize with appropriate data

        // Set up some test messages
        LocalDateTime now = LocalDateTime.now();
        Message message1 = new Message();
        message1.setCreatedAt(now);
        Message message2 = new Message();
        message2.setCreatedAt(now.minusHours(1));

        when(messagesRepository.findTopBySenderAndReceiverOrderByCreatedAtDesc(sender, receiver))
                .thenReturn(Optional.of(message1));
        when(messagesRepository.findTopBySenderAndReceiverOrderByCreatedAtDesc(receiver, sender))
                .thenReturn(Optional.of(message2));

        Message result = messagesService.findBySenderAndReceiverLastMessage(sender, receiver);

        assertEquals(message1, result);
    }
}