package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> findByPhoneNumber(String number) {
        return usersRepository.findByPhoneNumber(number);
    }

    public Optional<User> findById(int id) {
        return usersRepository.findById(id);
    }

    @Transactional
    public void update(User user) {
        usersRepository.save(user);
    }

    public List<User> findBySearchLine(String name, String surname) {
        return usersRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name, surname);
    }


}
