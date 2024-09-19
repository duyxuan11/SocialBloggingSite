package org.example.socialbloggingsite.articles.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.socialbloggingsite.user.model.User;
import org.hibernate.annotations.UpdateTimestamp;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String title;
    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = true)
    private String published;
    @Column(nullable = true)
    private String category;

    @UpdateTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }

    public Article setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPublished() {
        return published;
    }

    public Article setPublished(String published) {
        this.published = published;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Article setCategory(String category) {
        this.category = category;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Article setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Article setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Article setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
