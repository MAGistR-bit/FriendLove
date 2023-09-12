package com.gorbatenkov.FriendLove.models;

import javax.persistence.*;

@Entity
@Table(name = "group_contact")
public class GroupContact {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "users",referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "groups",referencedColumnName = "id")
    private Group group;

    public GroupContact() {
    }

    public GroupContact(User user, Group group) {
        this.user = user;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
