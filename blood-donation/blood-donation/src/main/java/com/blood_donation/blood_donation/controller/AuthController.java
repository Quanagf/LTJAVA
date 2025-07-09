package com.blood_donation.blood_donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blood_donation.blood_donation.dto.UserRegistrationDto;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto());
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("userDto") UserRegistrationDto userDto,
                                      BindingResult bindingResult,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
            return "register"; // Quay lại form nếu có lỗi xác thực
        }
        try {
            userService.registerNewUser(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký tài khoản thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("bloodTypes", bloodTypeRepository.findAll());
            return "register";
        }
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}