package com.gorbatenkov.FriendLove.DTO;

import com.gorbatenkov.FriendLove.models.Group;
import com.gorbatenkov.FriendLove.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PostDTOTest {

    private PostDTO postDTO;

    @BeforeEach
    public void setUp() {
        // Инициализируем объект PostDTO для тестирования
        postDTO = new PostDTO();
    }

    @Test
    public void testGetSetId() {
        assertEquals(0, postDTO.getId());
        postDTO.setId(123);
        assertEquals(123, postDTO.getId());
    }

    @Test
    public void testGetSetCreatedAt() {
        assertNull(postDTO.getCreatedAt());
        LocalDateTime newDateTime = LocalDateTime.of(2023, 9, 1, 12, 0);
        postDTO.setCreatedAt(newDateTime);
        assertEquals(newDateTime, postDTO.getCreatedAt());
    }

    @Test
    public void testGetSetPost() {
        assertNull(postDTO.getPost());
        postDTO.setPost("Test Post");
        assertEquals("Test Post", postDTO.getPost());
    }

    @Test
    public void testGetSetRating() {
        assertEquals(0, postDTO.getRating());
        postDTO.setRating(5);
        assertEquals(5, postDTO.getRating());
    }

    @Test
    public void testGetSetAuthor() {
        assertNotNull(postDTO.getAuthor());
        User newUser = new User();
        newUser.setId(2);
        newUser.setName("Jane");
        newUser.setSurname("Smith");
        newUser.setAvatar("user_avatar_new.jpg");
        postDTO.setAuthor(newUser);
        assertEquals(newUser, postDTO.getAuthor());
    }

    @Test
    public void testGetSetGroup() {
        assertNotNull(postDTO.getGroup());
        Group newGroup = new Group();
        newGroup.setId(3);
        newGroup.setName("New Group");
        newGroup.setAvatar("group_avatar.jpg");
        postDTO.setGroup(newGroup);
        assertEquals(newGroup, postDTO.getGroup());
    }

    @Test
    public void testGetSetGroupContactsId() {
        assertNotNull(postDTO.getGroupContactsId());
        Set<Integer> newGroupContactsId = new HashSet<>();
        newGroupContactsId.add(1);
        newGroupContactsId.add(2);
        postDTO.setGroupContactsId(newGroupContactsId);
        assertEquals(newGroupContactsId, postDTO.getGroupContactsId());
    }

    @Test
    public void testGetJsonn() throws JsonProcessingException {
        // Проверка, что метод getJsonn() не возвращает null
        assertNotNull(postDTO.getJsonn());

        // Проверка, что метод getJsonn() возвращает пустую строку
        assertEquals("", postDTO.getJsonn());
    }
}