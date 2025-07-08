package com.blood_donation.blood_donation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blood_donation.blood_donation.dto.BlogCreationDto;
import com.blood_donation.blood_donation.entity.Blog;

public interface BlogService {
    List<Blog> findLatestPublishedBlogs(int limit);
    Page<Blog> findAllPublished(Pageable pageable);
    Optional<Blog> findPublishedById(Integer id);
    void createBlog(BlogCreationDto dto, String username);
    List<Blog> findBlogsByAuthor(String username);
    Page<Blog> findPendingBlogs(Pageable pageable);
    void approveBlog(Integer blogId);
    void rejectBlog(Integer blogId);
    Page<Blog> findAllBlogs(Pageable pageable);
    Optional<Blog> findById(Integer id);
    void updateBlogByAdmin(Integer blogId, BlogCreationDto blogDto);
    void deleteBlog(Integer blogId);
}