package com.gorbatenkov.FriendLove.services;

import com.gorbatenkov.FriendLove.models.Friend;
import com.gorbatenkov.FriendLove.repositories.FriendRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Данный класс служит для управления друзьями
 * в социальной сети.
 */
@Service
@Transactional(readOnly = true)
public class FriendsService {
    /**
     * Инжектирует репозиторий для доступа к данным о друзьях
     */
    private final FriendRepository friendRepository;

    /**
     * Конструктор
     * @param friendRepository репозиторий
     */
    public FriendsService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    /**
     * Вносит изменения в
     * базу данных (сохраняет пользователя)
     * @param friend друг
     */
    @Transactional
    public void save(Friend friend) {
        friendRepository.save(friend);
    }
}
