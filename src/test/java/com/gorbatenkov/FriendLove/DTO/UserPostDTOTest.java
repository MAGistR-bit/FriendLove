package com.gorbatenkov.FriendLove.DTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gorbatenkov.FriendLove.models.PostComment;
import com.gorbatenkov.FriendLove.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class UserPostDTOTest {

    private UserPostDTO userPostDTO;

    @BeforeEach
    public void setUp() {
        // Создаем объект User для автора
        User author = new User();
        author.setId(1);
        author.setName("John");
        author.setSurname("Doe");
        author.setAvatar("user_avatar.jpg");

        // Создаем список комментариев к посту
        List<PostComment> comments = new ArrayList<>();
        comments.add(new PostComment(1, LocalDateTime.now(), "Comment 1"));
        comments.add(new PostComment(2, LocalDateTime.now(), "Comment 2"));

        // Инициализируем UserPostDTO для тестирования
        userPostDTO = new UserPostDTO(
                LocalDateTime.now(),
                "Test Post",
                5,
                author);
    }

    @Test
    public void testGetJsonn() throws JsonProcessingException {
        String json = userPostDTO.getJsonn();
        assertNotNull(json);
        assertTrue(json.contains("\"post\":\"Test Post\""));
        assertTrue(json.contains("\"rating\":5"));
    }

    @Test
    public void testGetSetId() {
        assertEquals(0, userPostDTO.getId());
        userPostDTO.setId(123);
        assertEquals(123, userPostDTO.getId());
    }

    @Test
    public void testGetSetCreatedAt() {
        assertNotNull(userPostDTO.getCreatedAt());
        LocalDateTime newDateTime = LocalDateTime.of(2023, 9, 1, 12, 0);
        userPostDTO.setCreatedAt(newDateTime);
        assertEquals(newDateTime, userPostDTO.getCreatedAt());
    }

    @Test
    public void testGetSetPost() {
        assertEquals("Test Post", userPostDTO.getPost());
        userPostDTO.setPost("Updated Post");
        assertEquals("Updated Post", userPostDTO.getPost());
    }

    @Test
    public void testGetSetRating() {
        assertEquals(5, userPostDTO.getRating());
        userPostDTO.setRating(10);
        assertEquals(10, userPostDTO.getRating());
    }

    @Test
    public void testGetSetAuthor() {
        assertNotNull(userPostDTO.getAuthor());
        User newUser = new User();
        newUser.setId(2);
        newUser.setName("Jane");
        newUser.setSurname("Smith");
        newUser.setAvatar("user_avatar_new.jpg");
        userPostDTO.setAuthor(newUser);
        assertEquals(newUser, userPostDTO.getAuthor());
    }

}