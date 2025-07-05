// src/main/java/test/blood/donation/model/dto/BlogResponseDto.java
package test.blood.donation.model.dto;

import java.time.LocalDateTime;

public class BlogResponseDto {
    private int id;
    private String title;
    private String content;
    private String authorName; // Chỉ lấy tên tác giả, không lộ cả object User
    private LocalDateTime createdAt;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}