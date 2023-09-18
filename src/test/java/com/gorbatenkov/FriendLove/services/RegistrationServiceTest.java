package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationService = new RegistrationService(usersRepository, passwordEncoder);
    }

    @Test
    void testRegister() {
        User user = new User();
        user.setPassword("plainPassword");

        // Mock behavior for the password encoder
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        // Mock behavior for the repository save
        when(usersRepository.save(user)).thenReturn(user);

        int userId = registrationService.register(user);

        // Verify that the user's password was encoded
        verify(passwordEncoder, times(1)).encode("plainPassword");

        // Verify that the user's role and other default properties are set
        assertEquals("ROLE_USER", user.getRole());
        assertEquals("Я новый пользователь!", user.getStatus());
        assertEquals("", user.getLocation());
        assertEquals("", user.getUniversity());
        assertEquals("", user.getJob());

        // Verify that the repositories save method was called
        verify(usersRepository, times(1)).save(user);

        // Ensure the returned user id is correct
        assertEquals(user.getId(), userId);
    }
}