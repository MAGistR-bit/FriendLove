package com.gorbatenkov.FriendLove.utils;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.services.UserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserValidatorTest {

    private UserValidator userValidator;

    @Mock
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void setUp() {
        userDetailsService = mock(UserDetailsService.class);
        userValidator = new UserValidator(userDetailsService);
    }

    @Test
    public void testSupports() {
        assertTrue(userValidator.supports(User.class));
        assertFalse(userValidator.supports(Object.class));
    }


    @Test
    public void testValidateWithNonExistingUser() {
        User user = new User();
        user.setPhoneNumber("newPhoneNumber");

        when(userDetailsService.loadUserByUsername("newPhoneNumber")).thenReturn(null);

        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);

        assertTrue(errors.hasErrors());

        verify(userDetailsService, times(1)).loadUserByUsername("newPhoneNumber");
    }
}