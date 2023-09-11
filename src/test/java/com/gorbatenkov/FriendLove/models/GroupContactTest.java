package com.gorbatenkov.FriendLove.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GroupContactTest {

    private GroupContact groupContact;

    @BeforeEach
    public void setUp() {
        // Инициализируем объект GroupContact для тестирования
        User user = new User(1, "Александр", "Никулин",
                "nikulin@gmail.com", "+79999999999",
                "12345", "/img/",
                LocalDate.now(), "Брянск",
                "teacher", " ", " ", "Герой СВО", "USER");
        Group group = new Group(1, LocalDateTime.now(), "Test Group",
                "Description", "avatar.jpg");
        groupContact = new GroupContact(user, group);
    }

    @Test
    public void testGetSetId() {
        assertEquals(0, groupContact.getId());
        groupContact.setId(123);
        assertEquals(123, groupContact.getId());
    }

    @Test
    public void testGetSetUser() {
        assertNotNull(groupContact.getUser());
        assertEquals("Александр", groupContact.getUser().getName());

        // Устанавливаем нового пользователя и проверяем
        User newUser = new User(2, "User2", "Torres",
                "ivan@gmail.com", "+79105558803",
                "12345", "/img/",
                LocalDate.now(), "Moscow",
                "teacher", " ", " ", "Новичок", "USER");
        groupContact.setUser(newUser);
        assertEquals(newUser, groupContact.getUser());
    }

    @Test
    public void testGetSetGroup() {
        assertNotNull(groupContact.getGroup());
        assertEquals("Test Group", groupContact.getGroup().getName());

        // Устанавливаем новую группу и проверяем
        Group newGroup = new Group(2, LocalDateTime.now(), "New Group", "New Description", "new_avatar.jpg");
        groupContact.setGroup(newGroup);
        assertEquals(newGroup, groupContact.getGroup());
    }
}