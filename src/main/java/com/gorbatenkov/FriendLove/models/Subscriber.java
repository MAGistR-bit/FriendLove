package com.gorbatenkov.FriendLove.models;

import javax.persistence.*;

/**
 * Сущность (Подписчик)
 */
@Entity
@Table(name = "subscriber")
public class Subscriber {
    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Пользователь
     */
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private User user;
    /**
     * Подписчик
     */
    @ManyToOne
    @JoinColumn(name = "subscriber", referencedColumnName = "id")
    private User subscriber;

    /**
     * Конструктор по умолчанию
     */
    public Subscriber() {
    }

    /**
     * Конструктор с параметрами
     *
     * @param user       пользователь
     * @param subscriber подписчик
     */
    public Subscriber(User user, User subscriber) {
        this.user = user;
        this.subscriber = subscriber;
    }

    /**
     * Возвращает идентификатор
     *
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает новое значение идентификатора
     *
     * @param id новое значение ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает пользователя
     *
     * @return пользователь
     */
    public User getUser() {
        return user;
    }

    /**
     * Изменяет пользователя
     *
     * @param user новый пользователь
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Получает подписчика (объект класса User)
     *
     * @return подписчик
     */
    public User getSubscriber() {
        return subscriber;
    }

    /**
     * Устанавливает нового подписчика
     *
     * @param subscriber новый подписчик
     */
    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }
}
