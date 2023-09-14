package com.gorbatenkov.FriendLove.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность (Комментарий пользователя к фотографии)
 */
@Entity
@Table(name = "user_photo_comment")
public class UserPhotoComment {
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
     * Комментарий
     */
    @Column(name = "comment")
    private String comment;
    /**
     * Фото пользователя
     */
    @ManyToOne
    @JoinColumn(name = "user_photo", referencedColumnName = "id")
    @JsonIgnore
    private UserPhoto userPhoto;
    /**
     * Автор
     */
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;

    /**
     * Конструктор по умолчанию
     */
    public UserPhotoComment() {
    }

    /**
     * Конструктор с параметрами
     *
     * @param id        идентификатор
     * @param createdAt дата и время создания
     * @param comment комментарий
     */
    public UserPhotoComment(int id,
                            LocalDateTime createdAt,
                            String comment) {
        this.id = id;
        this.createdAt = createdAt;
        this.comment = comment;
    }

    /**
     * Метод, позволяющий получить идентификатор
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Метод, позволяющий установить новое значение идентификатору
     * @param id новое значение ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получает дату и время создания
     * @return дата и время создания
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Изменяет дату и время создания
     * @param createdAt новая дата и время
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Отображает комментарий пользователя
     * @return комментарий
     */
    public String getComment() {
        return comment;
    }

    /**
     * Изменяет комментарий
     * @param comment новый комментарий
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Получает фото пользователя
     * @return фото пользователя
     */
    public UserPhoto getUserPhoto() {
        return userPhoto;
    }

    /**
     * Изменяет фотографию пользователя
     * @param userPhoto новая фотография
     */
    public void setUserPhoto(UserPhoto userPhoto) {
        this.userPhoto = userPhoto;
    }

    /**
     * Отображает автора (объект User) публикации
     * @return автор
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Изменяет автора публикации
     * @param author новый автор
     */
    public void setAuthor(User author) {
        this.author = author;
    }
}
