package com.blood_donation.blood_donation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blood_donation.blood_donation.dto.BlogCreationDto;
import com.blood_donation.blood_donation.entity.Blog;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.BlogRepository;
import com.blood_donation.blood_donation.repository.UserRepository;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Blog> findLatestPublishedBlogs(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return blogRepository.findByStatusOrderByCreatedAtDesc(Blog.Status.PUBLISHED, pageable).getContent();
    }
    
    @Override
    public Page<Blog> findAllPublished(Pageable pageable) {
        return blogRepository.findByStatusOrderByCreatedAtDesc(Blog.Status.PUBLISHED, pageable);
    }

    @Override
    public Optional<Blog> findPublishedById(Integer id) {
        return blogRepository.findById(id).filter(blog -> blog.getStatus() == Blog.Status.PUBLISHED);
    }

    @Override
    public void createBlog(BlogCreationDto dto, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng."));
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setAuthor(author);
        blog.setStatus(Blog.Status.PENDING_APPROVAL);
        blogRepository.save(blog);
    }

    @Override
    public List<Blog> findBlogsByAuthor(String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng."));
        return blogRepository.findByAuthorOrderByCreatedAtDesc(author);
    }

    @Override
    public Page<Blog> findPendingBlogs(Pageable pageable) {
        return blogRepository.findByStatus(Blog.Status.PENDING_APPROVAL, pageable);
    }

    @Override
    public void approveBlog(Integer blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết."));
        blog.setStatus(Blog.Status.PUBLISHED);
        blogRepository.save(blog);
    }

    @Override
    public void rejectBlog(Integer blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết."));
        blog.setStatus(Blog.Status.REJECTED);
        blogRepository.save(blog);
    }

    @Override
    public Page<Blog> findAllBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Integer id) {
        return blogRepository.findById(id);
    }

    @Override
    public void updateBlogByAdmin(Integer blogId, BlogCreationDto blogDto) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết với ID: " + blogId));
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Integer blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new RuntimeException("Không tìm thấy bài viết với ID: " + blogId);
        }
        blogRepository.deleteById(blogId);
    }
}