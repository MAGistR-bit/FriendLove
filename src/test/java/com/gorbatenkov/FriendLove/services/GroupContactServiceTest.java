package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.GroupContact;
import com.gorbatenkov.FriendLove.repositories.GroupContactsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GroupContactServiceTest {

    // Mock объект репозитория
    @Mock
    private GroupContactsRepository groupContactsRepository;

    private GroupContactService groupContactService;

    @BeforeEach
    public void setUp() {
        // Инициализация mock объектов и сервиса
        MockitoAnnotations.initMocks(this);
        groupContactService = new GroupContactService(groupContactsRepository);
    }

    @Test
    public void saveGroupContact_shouldCallRepositorySave() {
        // Создаем тестовый GroupContact
        GroupContact groupContact = new GroupContact();
        // Вызываем метод, который мы хотим протестировать
        groupContactService.save(groupContact);
        // Проверяем, что метод save() был вызван у репозитория
        verify(groupContactsRepository).save(groupContact);
    }
}