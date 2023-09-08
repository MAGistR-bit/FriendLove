package com.gorbatenkov.FriendLove.models;

import javax.persistence.*;

/**
 * Сущность (Entity).
 * Используется для моделирования отношений
 * между пользователями и их друзьями
 * в социальной сети
 */
@Entity
// Данные об объектах этого класса
// будут храниться в этой таблице
@Table(name = "friends")
public class Friend {
    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Связь между классами Friend и User
     */
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "friend", referencedColumnName = "id")
    private User friend;

    public Friend() {
    }

    /**
     * Конструктор, который позволяет создавать объекты с заданными пользователями.
     * @param user пользователь
     * @param friend друг
     */
    public Friend(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
