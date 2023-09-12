package com.gorbatenkov.FriendLove.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_post_comment")
public class GroupPostComment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "group_post",referencedColumnName = "id")
    @JsonIgnore
    private GroupPost groupPost;
    @ManyToOne
    @JoinColumn(name = "author",referencedColumnName = "id")
    private User author;

    public GroupPostComment() {
    }

    public GroupPostComment(int id, LocalDateTime createdAt, String comment) {
        this.id = id;
        this.createdAt = createdAt;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public GroupPost getGroupPost() {
        return groupPost;
    }

    public void setGroupPost(GroupPost groupPost) {
        this.groupPost = groupPost;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
