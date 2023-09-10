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
 * Пост/публикация (Post)
 */
@Entity
@Table(name = "post")
public class Post {
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
     * Пост
     */
    @Column(name = "post")
    private String post;
    /**
     * Рейтинг
     */
    @Column(name = "rating")
    private int rating;
    /**
     * Автор
     */
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;

    /**
     * Оставлять комментарии
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private List<PostComment> postComments;

    public Post() {
    }

    public Post(int id, LocalDateTime createdAt, String post, int rating) {
        this.id = id;
        this.createdAt = createdAt;
        this.post = post;
        this.rating = rating;
    }

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

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }
}
