package com.blood_donation.blood_donation.controller;

import com.blood_donation.blood_donation.entity.Blog;
import com.blood_donation.blood_donation.entity.MedicalCenter;
import com.blood_donation.blood_donation.service.BlogService;
import com.blood_donation.blood_donation.service.MedicalCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MedicalCenterService medicalCenterService;

    // Thêm lại BlogService
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        // Lấy thông tin trung tâm y tế
        MedicalCenter center = medicalCenterService.findPrimaryCenter();
        model.addAttribute("center", center);

        // Lấy 3 bài blog mới nhất và thêm vào model
        List<Blog> latestBlogs = blogService.findLatestPublishedBlogs(3);
        model.addAttribute("latestBlogs", latestBlogs);

        // Trả về tên của file view (index.html)
        return "index";
    }
}