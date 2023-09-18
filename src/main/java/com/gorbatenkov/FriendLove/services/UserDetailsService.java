package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByPhoneNumber(phoneNumber);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }

        return new com.gorbatenkov.FriendLove.security.UserDetails(user.get());
    }
}
