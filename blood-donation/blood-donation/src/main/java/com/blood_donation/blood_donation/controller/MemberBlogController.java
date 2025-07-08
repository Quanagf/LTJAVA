package com.blood_donation.blood_donation.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blood_donation.blood_donation.dto.BlogCreationDto;
import com.blood_donation.blood_donation.entity.Blog;
import com.blood_donation.blood_donation.service.BlogService;

@Controller
@RequestMapping("/member/blogs")
public class MemberBlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/my-posts")
    public String showMyPosts(Model model, Principal principal) {
        List<Blog> myPosts = blogService.findBlogsByAuthor(principal.getName());
        model.addAttribute("myPosts", myPosts);
        return "member/my-blog-posts";
    }

    @GetMapping("/new")
    public String showCreateBlogForm(Model model) {
        model.addAttribute("blogDto", new BlogCreationDto());
        return "member/blog-create-form";
    }

    @PostMapping("/save")
    public String saveBlogPost(@ModelAttribute("blogDto") BlogCreationDto blogDto,
                               Principal principal,
                               RedirectAttributes redirectAttributes) {
        try {
            blogService.createBlog(blogDto, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage", "Đã gửi bài viết thành công! Vui lòng chờ Staff duyệt.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/member/blogs/my-posts";
    }
}