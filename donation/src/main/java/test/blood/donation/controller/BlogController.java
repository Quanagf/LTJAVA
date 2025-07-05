// src/main/java/test/blood/donation/controller/BlogController.java
package test.blood.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.blood.donation.model.dto.BlogRequestDto;
import test.blood.donation.model.dto.BlogResponseDto;
import test.blood.donation.service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogResponseDto> createBlog(@RequestBody BlogRequestDto requestDto) {
        BlogResponseDto newBlog = blogService.createBlog(requestDto);
        return ResponseEntity.ok(newBlog);
    }

    @GetMapping
    public ResponseEntity<List<BlogResponseDto>> getAllBlogs() {
        List<BlogResponseDto> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDto> getBlogById(@PathVariable Integer id) {
        BlogResponseDto blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }
}