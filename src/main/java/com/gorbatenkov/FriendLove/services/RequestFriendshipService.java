package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.RequestFriendship;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.RequestsFriendshipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RequestFriendshipService {
    private final RequestsFriendshipRepository requestsFriendshipRepository;

    public RequestFriendshipService(RequestsFriendshipRepository requestsFriendshipRepository) {
        this.requestsFriendshipRepository = requestsFriendshipRepository;
    }

    @Transactional
    public void save(RequestFriendship requestFriendship) {
        requestsFriendshipRepository.save(requestFriendship);
    }

    public List<User> findBySender(User sender) {
        List<User> receiversList = new ArrayList<>();
        requestsFriendshipRepository.findBySender(sender).forEach((c) -> receiversList.add(c.getReceiver()));
        return receiversList;
    }

    public List<User> findByReceiver(User receiver) {
        List<User> sendersList = new ArrayList<>();
        requestsFriendshipRepository.findByReceiver(receiver).forEach((c) -> sendersList.add(c.getSender()));
        return sendersList;
    }

    public Optional<RequestFriendship> findBySenderAndReceiver(User sender, User receiver) {
        return requestsFriendshipRepository.findBySenderAndReceiver(sender, receiver);
    }

    @Transactional
    public void deleteBySenderAndReceiver(User sender, User receiver) {
        requestsFriendshipRepository.deleteBySenderAndReceiver(sender, receiver);
    }
}
