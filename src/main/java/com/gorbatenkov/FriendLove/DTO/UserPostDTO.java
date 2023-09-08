package com.gorbatenkov.FriendLove.DTO;

import com.gorbatenkov.FriendLove.models.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

/**
 * Предназначен для удобного представления
 * и обработки данных о постах, созданных пользователями
 * в социальной сети.
 * Расширяет класс PostDTO.
 */
public class UserPostDTO extends PostDTO {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Дата и время создания поста
     */
    private LocalDateTime createdAt;

    /**
     * Текст поста/публикации
     */
    private String post;

    /**
     * Рейтинг (количество лайков)
     */
    private int rating;
    /**
     * Автор поста (объект класса User)
     */
    private User author;

    public UserPostDTO() {
    }

    /**
     * Конструктор, который принимает аргументы для всех полей класса и инициализирует их.
     *
     * @param createdAt дата и время создания
     * @param post      пост/публикация
     * @param rating    рейтинг
     * @param author    автор
     */
    public UserPostDTO(LocalDateTime createdAt,
                       String post,
                       int rating,
                       User author) {
        this.createdAt = createdAt;
        this.post = post;
        this.rating = rating;
        this.author = author;
    }

    /**
     * Использует библиотеку Jackson
     * для сериализации объекта `UserPostDTO` в JSON-строку
     *
     * @return JSON-строка
     * @throws JsonProcessingException исключение, которое может возникнуть
     */
    @Override
    public String getJsonn() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Преобразование объекта в JSON
        return mapper.writeValueAsString(this);
    }

    // Геттеры и сеттеры для доступа к полям класса
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

}
