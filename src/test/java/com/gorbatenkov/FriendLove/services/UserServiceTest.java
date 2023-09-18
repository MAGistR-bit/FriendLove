package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByPhoneNumber() {
        String phoneNumber = "1234567890";
        User user = new User();
        user.setPhoneNumber(phoneNumber);

        when(usersRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByPhoneNumber(phoneNumber);

        assertTrue(foundUser.isPresent());
        assertEquals(phoneNumber, foundUser.get().getPhoneNumber());
    }

    @Test
    void testFindById() {
        int userId = 1;
        User user = new User();
        user.setId(userId);

        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findById(userId);

        assertTrue(foundUser.isPresent());
        assertEquals(userId, foundUser.get().getId());
    }

    @Test
    void testUpdate() {
        User user = new User();
        userService.update(user);
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    void testFindBySearchLine() {
        String name = "John";
        String surname = "Doe";

        List<User> usersList = new ArrayList<>();
        usersList.add(new User());
        usersList.add(new User());

        when(usersRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name, surname)).thenReturn(usersList);

        List<User> foundUsers = userService.findBySearchLine(name, surname);

        assertEquals(2, foundUsers.size());
    }
}