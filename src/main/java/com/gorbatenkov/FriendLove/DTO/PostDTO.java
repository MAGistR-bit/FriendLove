package com.gorbatenkov.FriendLove.DTO;

import com.gorbatenkov.FriendLove.models.Group;
import com.gorbatenkov.FriendLove.models.GroupPostComment;
import com.gorbatenkov.FriendLove.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Представление данных о постах
 * в социальной сети
 */
public class PostDTO {
    /**
     * Идентификатор поста/публикации
     */
    private int id;
    /**
     * Дата и время создания поста
     */
    private LocalDateTime createdAt;
    /**
     * Текс публикации
     */
    private String post;
    /**
     * Набор идентификаторов контактов группы
     */
    private Set<Integer> groupContactsId;
    /**
     * Список комментариев к посту
     */
    private final List<GroupPostComment> groupPostComments;
    /**
     * Рейтинг поста
     */
    private int rating;
    /**
     * Автор
     */
    private User author;
    /**
     * Группа, к которой относится пост
     */
    private Group group;

    /* Установка начальных значений
    (для избежания ошибок) */
    {
        author = new User();
        author.setId(-1);
        author.setName("TestName");
        author.setSurname("UserSurname");
        author.setAvatar("Avatar");
        group = new Group();
        group.setName("TestName");
        group.setAvatar("Avatar");

        groupContactsId = new HashSet<>();
        groupPostComments = new ArrayList<>();
    }


    public String getJsonn() throws JsonProcessingException {
        return "";
    }

    // Геттеры и сеттеры для доступа к полям класса
    public Set<Integer> getGroupContactsId() {
        return groupContactsId;
    }

    public List<GroupPostComment> getGroupPostComments() {
        return groupPostComments;
    }

    public void setGroupContactsId(Set<Integer> groupContactsId) {
        this.groupContactsId = groupContactsId;
    }

    /**
     * Получить идентификатор
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Установить новое значение идентификатора
     * @param id новое значение id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить пост
     * @return текст публикации
     */
    public String getPost() {
        return post;
    }

    /**
     * Изменить пост
     * @param post новый текст
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * Получить рейтинг (количество лайков)
     * @return рейтинг
     */
    public int getRating() {
        return rating;
    }

    /**
     * Изменить рейтинг
     * @param rating новое значение
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Получить автора публикации
     * @return автор публикации
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Изменить автора публикации/поста
     * @param author новый автор
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Получить группу/сообщество
     * @return группа
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Установить группу
     * @param group новая группа
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Получить дату и время создания поста
     * @return дата и время создания публикации
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Изменить дату и время
     * @param createdAt новые значения для даты создания
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
