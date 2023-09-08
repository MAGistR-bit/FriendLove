package com.gorbatenkov.FriendLove.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Модель группы в социальной сети.
 * Используется для взаимодействия с БД, а также
 * для представления данных на стороне приложения.
 */
@Entity
@Table(name = "groups")
public class Group {
    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    // Автоматическая генерация значений
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Дата создания
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    /**
     * Название группы
     */
    @Column(name = "name")
    private String name;
    /**
     * Описание группы
     */
    @Column(name = "description")
    private String description;
    /**
     * Аватарка
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * Отношение "многие ко многим" с сущностью User
     * через промежуточную таблицу group_contact
     */
    @ManyToMany
    @JoinTable(
            name = "group_contact",
            joinColumns = @JoinColumn(name = "groups"),
            inverseJoinColumns = @JoinColumn(name = "users"))
    // Игнорирование сериализации и десериализации полей
    @JsonIgnore
    private List<User> contacts;

    @ManyToMany
    @JoinTable(
            name = "subscription_group",
            joinColumns = @JoinColumn(name = "groups"),
            inverseJoinColumns = @JoinColumn(name = "subscriber"))
    @JsonIgnore
    private List<User> members;

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<GroupPhoto> groupPhotos;
    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<GroupPost> groupPosts;

    /**
     * Конструктор по умолчанию
     */
    public Group() {
    }

    /**
     * Конструктор с параметрами
     * @param id идентификатор
     * @param createdAt дата создания
     * @param name название
     * @param description описание
     * @param avatar аватарка
     */
    public Group(int id, LocalDateTime createdAt, String name, String description, String avatar) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
    }

    // Геттеры и сеттеры для доступа к полям
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<GroupPhoto> getGroupPhotos() {
        return groupPhotos;
    }

    public void setGroupPhotos(List<GroupPhoto> groupPhotos) {
        this.groupPhotos = groupPhotos;
    }

    public List<GroupPost> getGroupPosts() {
        return groupPosts;
    }

    public void setGroupPosts(List<GroupPost> groupPosts) {
        this.groupPosts = groupPosts;
    }


    @Override
    public int hashCode() {
        return id;
    }
}
