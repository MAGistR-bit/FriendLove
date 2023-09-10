package com.gorbatenkov.FriendLove.models;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность (Message)
 */
@Entity
@Table(name = "message")
public class Message {
    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Дата и время создания
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    /**
     * Сообщение
     */
    @Column(name = "message")
    private String message;
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

    public Message() {
    }

    public Message(LocalDateTime createdAt, String message) {
        this.createdAt = createdAt;
        this.message = message;
    }

    /* Геттеры и сеттеры для доступа к полям */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
