package com.gorbatenkov.FriendLove.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сущность (Фото пользователя)
 */
@Entity
@Table(name = "user_photo")
public class UserPhoto {
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
     * Месторасположение фотографии
     */
    @Column(name = "photo_path")
    private String photoPath;
    /**
     * Рейтинг (количество лайков)
     */
    @Column(name = "rating")
    private int rating;
    /**
     * Пользователь
     */
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private User user;
    /**
     * Список комментариев
     */
    @OneToMany(mappedBy = "userPhoto")
    private List<UserPhotoComment> userPhotoComments;

    /**
     * Конструктор по умолчанию
     */
    public UserPhoto() {
    }

    /**
     * Конструктор с параметрами
     *
     * @param id        идентификатор
     * @param createdAt дата и время создания
     * @param photoPath месторасположение фотографии
     * @param rating рейтинг
     */
    public UserPhoto(int id,
                     LocalDateTime createdAt,
                     String photoPath,
                     int rating) {
        this.id = id;
        this.createdAt = createdAt;
        this.photoPath = photoPath;
        this.rating = rating;
    }

    /**
     * Используется для сериализации объекта UserPhoto
     * в формат JSON с использованием Jackson (ObjectMapper)
     * @return строка JSON
     * @throws JsonProcessingException исключение, которое может возникнуть
     */
    public String getJsonn() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(mapper.getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.writeValueAsString(this);
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserPhotoComment> getUserPhotoComments() {
        return userPhotoComments;
    }

    public void setUserPhotoComments(List<UserPhotoComment> userPhotoComments) {
        this.userPhotoComments = userPhotoComments;
    }
}
