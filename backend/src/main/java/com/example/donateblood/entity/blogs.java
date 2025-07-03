package com.example.donateblood.entity;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private users users;

    public blogs(){
        super();
    }
    public blogs(String title, String content, String status, String created_at, String update_at) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.created_at = LocalDateTime.now();
        this.update_at = LocalDateTime.now();
    }
    public void setUsers(users users) {
        this.users = users;
    }
    public users getUsers() {
        return users;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCreate_at(LocalDateTime create_at) {
        this.created_at = create_at;
    }
    public void setUpdated_at(LocalDateTime updated_at) {
        this.update_at = updated_at;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCreate_at() {
        return created_at;
    }
    public LocalDateTime getUpdated_at() {
        return update_at;
    }
}
