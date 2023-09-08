package com.gorbatenkov.FriendLove.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FriendTest {

    private Friend friend;

    @BeforeEach
    public void setUp() {
        // Инициализируем объект Friend для тестирования
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setSurname("Doe");
        user.setAvatar("user_avatar.jpg");

        User friendUser = new User();
        friendUser.setId(2);
        friendUser.setName("Jane");
        friendUser.setSurname("Smith");
        friendUser.setAvatar("friend_avatar.jpg");

        friend = new Friend(user, friendUser);
    }

    @Test
    public void testGetSetId() {
        assertEquals(0, friend.getId());
        friend.setId(123);
        assertEquals(123, friend.getId());
    }

    @Test
    public void testGetSetUser() {
        assertNotNull(friend.getUser());
        User newUser = new User();
        newUser.setId(3);
        newUser.setName("Alice");
        newUser.setSurname("Johnson");
        newUser.setAvatar("user_avatar_new.jpg");
        friend.setUser(newUser);
        assertEquals(newUser, friend.getUser());
    }

    @Test
    public void testGetSetFriend() {
        assertNotNull(friend.getFriend());
        User newFriendUser = new User();
        newFriendUser.setId(4);
        newFriendUser.setName("Bob");
        newFriendUser.setSurname("Brown");
        newFriendUser.setAvatar("friend_avatar_new.jpg");
        friend.setFriend(newFriendUser);
        assertEquals(newFriendUser, friend.getFriend());
    }
}