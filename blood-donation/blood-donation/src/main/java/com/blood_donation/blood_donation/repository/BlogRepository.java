package com.blood_donation.blood_donation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blood_donation.blood_donation.entity.Blog;
import com.blood_donation.blood_donation.entity.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByStatusOrderByCreatedAtDesc(Blog.Status status, Pageable pageable);
    List<Blog> findByAuthorOrderByCreatedAtDesc(User author);
    Page<Blog> findByStatus(Blog.Status status, Pageable pageable);
}