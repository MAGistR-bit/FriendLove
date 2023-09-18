package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Message;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.MessagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MessagesService {
    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Transactional
    public void save(Message message) {
        messagesRepository.save(message);
    }

    public List<Message> findBySender(User sender) {
        return messagesRepository.findBySender(sender);
    }

    public List<Message> findByReceiver(User receiver) {
        return messagesRepository.findByReceiver(receiver);
    }

    public List<Message> findBySenderAndReceiver(User sender, User receiver) {
        return messagesRepository.findBySenderAndReceiver(sender, receiver);
    }

    public Message findBySenderAndReceiverLastMessage(User sender, User receiver) {
        Message message1 = messagesRepository.findTopBySenderAndReceiverOrderByCreatedAtDesc(sender, receiver).orElse(null);
        Message message2 = messagesRepository.findTopBySenderAndReceiverOrderByCreatedAtDesc(receiver, sender).orElse(null);
        if (message1 == null) {
            return message2;
        } else if (message2 == null) {
            return message1;
        } else {
            switch (message1.getCreatedAt().compareTo(message2.getCreatedAt())) {
                case 0:
                case 1:
                    return message1;
                case -1:
                    return message2;
            }
        }
        return message1;
    }

}
