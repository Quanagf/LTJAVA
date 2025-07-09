package com.blood_donation.blood_donation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Không cần Autowired UserRepository ở đây nữa

    @GetMapping("/dashboard")
    public String showDashboard() {
        // ModelAttribute "loggedInUser" đã được cung cấp bởi GlobalControllerAdvice
        // nên không cần thêm vào model ở đây.
        return "dashboard";
    }
}