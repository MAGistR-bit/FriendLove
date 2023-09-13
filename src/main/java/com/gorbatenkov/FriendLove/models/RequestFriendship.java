package com.gorbatenkov.FriendLove.models;

import javax.persistence.*;

/**
 * Сущность (Заявка в друзья)
 */

@Entity
@Table(name = "request_friendship")
public class RequestFriendship {
    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Отправитель
     */
    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private User sender;
    /**
     * Получатель
     */
    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    private User receiver;

    /**
     * Конструктор по умолчанию
     */
    public RequestFriendship() {
    }

    /**
     * Конструктор с параметрами
     * @param sender отправитель
     * @param receiver получатель
     */
    public RequestFriendship(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Получает идентификатор
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает новое значению идентификатору
     * @param id новое значение для ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получает отправителя заявки в друзья
     * @return пользователь, который отправил заявку на дружбу
     */
    public User getSender() {
        return sender;
    }

    /**
     * Устанавливает нового пользователя, предлагающего дружить
     * @param sender отправитель заявки в друзья
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * Возвращает пользователя, которому предстоит принять
     * следующее решение: добавлять в друзья или нет
     * @return пользователь
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     * Изменяет пользователя, который принимает
     * заявку в друзья
     * @param receiver пользователь, который получает
     *                 заявку в друзья
     */
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
