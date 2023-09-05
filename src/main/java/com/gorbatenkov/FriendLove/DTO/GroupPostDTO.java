package com.gorbatenkov.FriendLove.DTO;

import com.gorbatenkov.FriendLove.models.Group;
import com.gorbatenkov.FriendLove.models.GroupPostComment;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Передача данных о постах в сообществах.
 * Используется наследование свойств и
 * методов из класса PostDTO
 */
public class GroupPostDTO extends PostDTO {

    /**
     * Идентификатор
     */
    private int id;
    /**
     * Дата создания
     */
    private LocalDateTime createdAt;
    /**
     * Текст публикации/поста
     */
    private String post;
    /**
     * Рейтинг
     */
    private int rating;
    /**
     * Группа
     */
    private Group group;
    /**
     * Комментарии к посту
     */
    private List<GroupPostComment> groupPostComments;
    /**
     * Идентификаторы контактов группы
     */
    private Set<Integer> groupContactsId;

    public GroupPostDTO() {
    }

    /**
     * Конструктор класса для создания объекта
     *
     * @param createdAt         дата создания
     * @param post              текст публикации
     * @param rating            рейтинг
     * @param group             группа
     * @param groupPostComments комментарии к посту
     */
    public GroupPostDTO(LocalDateTime createdAt, String post, int rating, Group group, List<GroupPostComment> groupPostComments) {
        this.createdAt = createdAt;
        this.post = post;
        this.rating = rating;
        this.group = group;
        this.groupPostComments = groupPostComments;
    }

    /**
     * Используется для сериализации объекта GroupPostDTO
     * в формат JSON с использованием библиотеки Jackson ObjectMapper.
     *
     * @return строка JSON
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

        return mapper.writeValueAsString(this);
    }

    /* Геттеры и сеттеры.
    Данные методы используются для получения
    и установки значений полей объекта GroupPostDTO.
    * */
    @Override
    public Set<Integer> getGroupContactsId() {
        return groupContactsId;
    }

    @Override
    public void setGroupContactsId(Set<Integer> groupContactsId) {
        this.groupContactsId = groupContactsId;
    }

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<GroupPostComment> getGroupPostComments() {
        return groupPostComments;
    }

    public void setGroupPostComments(List<GroupPostComment> groupPostComments) {
        this.groupPostComments = groupPostComments;
    }
}
