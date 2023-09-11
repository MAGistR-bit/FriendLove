package com.gorbatenkov.FriendLove.security;

import com.gorbatenkov.FriendLove.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Эти тесты проверяют, что методы UserDetails
 * правильно возвращают информацию о пользователе и его состоянии.
 */
public class UserDetailsTest {

    private User user;
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        userDetails = new UserDetails(user);
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    public void testGetPassword() {
        String password = userDetails.getPassword();
        assertEquals("password", password);
    }

    @Test
    public void testGetUsername() {
        String username = userDetails.getUsername();
        assertEquals("test@example.com", username);
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testGetUser() {
        User userFromUserDetails = userDetails.user();
        assertEquals(user, userFromUserDetails);
    }
}