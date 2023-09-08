package com.gorbatenkov.FriendLove.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {

    private Group group;

    @BeforeEach
    public void setUp() {
        // Инициализируем объект Group для тестирования
        group = new Group();
        group.setId(1);
        group.setCreatedAt(LocalDateTime.of(2023, 9, 1, 12, 0));
        group.setName("Test Group");
        group.setDescription("This is a test group.");
        group.setAvatar("group_avatar.jpg");

        List<User> contacts = new ArrayList<>();
        contacts.add(new User(1, "Dima", "Gates",
                "dima_gates@ya.ru", "+79105558889",
                "12345", "/img/",
                LocalDate.now(), "Moscow",
                "teacher", " ", " ", "Новичок", "USER"
                ));
        contacts.add(new User(2, "Ivan", "Torres",
                "ivan@gmail.com", "+79105558803",
                "12345", "/img/",
                LocalDate.now(), "Moscow",
                "teacher", " ", " ", "Новичок", "USER"));

        group.setContacts(contacts);

        List<User> members = new ArrayList<>();
        members.add(new User(3, "Earvin", "Face",
                "face@yahoo.com", "+79105554589",
                "12345", "/img/",
                LocalDate.now(), "Moscow",
                "teacher", " ", " ", "Новичок", "USER"));
        members.add(new User(4, "Lauren", "Joke",
                "lauren@maning.com", "+8005558889",
                "12345", "/img/",
                LocalDate.now(), "Moscow",
                "teacher", " ", " ", "Новичок", "USER"));
        group.setMembers(members);

        List<GroupPhoto> groupPhotos = new ArrayList<>();
        groupPhotos.add(new GroupPhoto(1, LocalDateTime.now(),
                "Photo_Path1", 0));
        groupPhotos.add(new GroupPhoto(2, LocalDateTime.now(), "Photo_Path2", 0));
        group.setGroupPhotos(groupPhotos);

        List<GroupPost> groupPosts = new ArrayList<>();
        groupPosts.add(new GroupPost(1, LocalDateTime.now(), "Post", 0));
        groupPosts.add(new GroupPost(2, LocalDateTime.now(), "Post2", 0));
        group.setGroupPosts(groupPosts);
    }

    @Test
    public void testGetSetId() {
        assertEquals(1, group.getId());
        group.setId(123);
        assertEquals(123, group.getId());
    }

    @Test
    public void testGetSetCreatedAt() {
        LocalDateTime newDateTime = LocalDateTime.of(2023, 10, 1, 14, 0);
        group.setCreatedAt(newDateTime);
        assertEquals(newDateTime, group.getCreatedAt());
    }

    @Test
    public void testGetSetName() {
        assertEquals("Test Group", group.getName());
        group.setName("New Group");
        assertEquals("New Group", group.getName());
    }

    @Test
    public void testGetSetDescription() {
        assertEquals("This is a test group.", group.getDescription());
        group.setDescription("New description");
        assertEquals("New description", group.getDescription());
    }

    @Test
    public void testGetSetAvatar() {
        assertEquals("group_avatar.jpg", group.getAvatar());
        group.setAvatar("new_avatar.jpg");
        assertEquals("new_avatar.jpg", group.getAvatar());
    }

    @Test
    public void testGetSetContacts() {
        assertNotNull(group.getContacts());
        assertEquals(2, group.getContacts().size());

        // Добавляем новый контакт и проверяем
        User newUser = new User(5, "User5", "Torres",
                "ivan@gmail.com", "+79105558803",
                "12345", "/img/",
                LocalDate.now(), "Moscow",
                "teacher", " ", " ", "Новичок", "USER");
        group.getContacts().add(newUser);
        assertEquals(3, group.getContacts().size());
        assertTrue(group.getContacts().contains(newUser));
    }

    @Test
    public void testGetSetMembers() {
        assertNotNull(group.getMembers());
        assertEquals(2, group.getMembers().size());

        // Добавляем нового участника и проверяем
        User newMember = new User(6, "User6", "Torres",
                "ivan@gmail.com", "+79105558803",
                "12345", "/img/",
                LocalDate.now(), "Moscow",
                "teacher", " ", " ", "Новичок", "USER");
        group.getMembers().add(newMember);
        assertEquals(3, group.getMembers().size());
        assertTrue(group.getMembers().contains(newMember));
    }

    @Test
    public void testGetSetGroupPhotos() {
        assertNotNull(group.getGroupPhotos());
        assertEquals(2, group.getGroupPhotos().size());

        // Добавляем новое фото и проверяем
        GroupPhoto newPhoto = new GroupPhoto(3, LocalDateTime.now(),
                "Photo3", 0);

        group.getGroupPhotos().add(newPhoto);
        assertEquals(3, group.getGroupPhotos().size());
        assertTrue(group.getGroupPhotos().contains(newPhoto));
    }

    @Test
    public void testGetSetGroupPosts() {
        assertNotNull(group.getGroupPosts());
        assertEquals(2, group.getGroupPosts().size());

        // Добавляем новый пост и проверяем
        GroupPost newPost = new GroupPost(3, LocalDateTime.now(), "Post3", 0);
        group.getGroupPosts().add(newPost);
        assertEquals(3, group.getGroupPosts().size());
        assertTrue(group.getGroupPosts().contains(newPost));
    }
}