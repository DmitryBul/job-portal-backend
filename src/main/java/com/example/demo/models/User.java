package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;  
    private String passwordHash;  
    private String email;
    private boolean isActive;
    private List<String> saved;
    private Date createdAt = new Date();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;  
    }

    public void setPassword(String password) {
        this.password = password;  
    }

    public String getPasswordHash() {
        return passwordHash;  
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;  
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<String> getSaved() {
        return saved;
    }

    public void setSaved(List<String> saved) {
        this.saved = saved;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}