package com.blood_donation.blood_donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blood_donation.blood_donation.dto.UserRegistrationDto;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BloodTypeRepository bloodTypeRepository; // Thêm repo này

    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Đưa một đối tượng DTO rỗng vào model để form có thể binding dữ liệu
        model.addAttribute("userDto", new UserRegistrationDto());
        // Lấy và đưa danh sách nhóm máu vào model
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
        return "register";
    }

    // Xử lý dữ liệu từ form đăng ký
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userDto") UserRegistrationDto userDto,
                                      RedirectAttributes redirectAttributes) {
        try {
            userService.registerNewUser(userDto);
            // Thêm một thuộc tính flash (chỉ tồn tại trong 1 request) để hiển thị thông báo thành công
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký tài khoản thành công! Vui lòng đăng nhập.");
            return "redirect:/login"; // Chuyển hướng về trang đăng nhập
        } catch (RuntimeException e) {
            // Nếu có lỗi (ví dụ: username tồn tại), thêm lỗi vào flash attribute
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("userDto", userDto); // Trả lại dữ liệu đã nhập
            return "redirect:/register"; // Chuyển hướng về lại trang đăng ký
        }
    }
    
    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}