package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDetailsServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        // Create a sample User
        User user = new User();
        user.setPhoneNumber("123456789"); // Assuming a valid phone number

        // Mock the repository's findByPhoneNumber method
        when(usersRepository.findByPhoneNumber("123456789")).thenReturn(Optional.of(user));

        // Call the service's loadUserByUsername method
        var userDetails = userDetailsService.loadUserByUsername("123456789");

        // Verify that the returned UserDetails is not null
        assertNotNull(userDetails);


    }

    @Test
    void testLoadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        // Mock the repository's findByPhoneNumber method for a non-existent user
        when(usersRepository.findByPhoneNumber("nonexistent")).thenReturn(Optional.empty());

        // Call the service's loadUserByUsername method for a non-existent user
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("nonexistent"));
    }
}