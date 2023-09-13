package com.gorbatenkov.FriendLove.models;

import javax.persistence.*;

/**
 * Сущность (Подписчики сообщества/группы)
 */
@Entity
@Table(name = "subscription_group")
public class SubscriptionGroup {
    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Группа/Сообщество
     */
    @ManyToOne
    @JoinColumn(name = "groups", referencedColumnName = "id")
    private Group group;
    /**
     * Подписчик
     */
    @ManyToOne
    @JoinColumn(name = "subscriber", referencedColumnName = "id")
    private User subscriber;

    /**
     * Конструктор по умолчанию
     */
    public SubscriptionGroup() {
    }

    /**
     * Конструктор с параметрами
     *
     * @param group      группа
     * @param subscriber подписчик
     */
    public SubscriptionGroup(Group group, User subscriber) {
        this.group = group;
        this.subscriber = subscriber;
    }

    /**
     * Метод, который позволяет получить идентификатор
     *
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Метод, который изменяет значение идентификатора
     *
     * @param id новое значение ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод, который получает группу
     *
     * @return группа/сообщество
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Метод, который устанавливает новое сообщество
     *
     * @param group группа/сообщество
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Метод, который возвращает подписчика сообщества
     *
     * @return подписчик
     */
    public User getSubscriber() {
        return subscriber;
    }

    /**
     * Метод, который устанавливает нового подписчика
     *
     * @param subscriber новый подписчик
     */
    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }
}
