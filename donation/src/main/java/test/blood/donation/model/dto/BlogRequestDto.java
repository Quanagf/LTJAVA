// src/main/java/test/blood/donation/model/dto/BlogRequestDto.java
package test.blood.donation.model.dto;

public class BlogRequestDto {
    private String title;
    private String content;
    private Integer userId; // ID của người dùng tạo bài viết

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}