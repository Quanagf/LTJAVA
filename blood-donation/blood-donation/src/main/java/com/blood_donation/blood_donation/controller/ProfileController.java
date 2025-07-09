package com.blood_donation.blood_donation.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blood_donation.blood_donation.dto.PasswordChangeDto;
import com.blood_donation.blood_donation.dto.UserProfileDto;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showProfileViewPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "member/profile-view";
    }

    @GetMapping("/edit")
    public String showProfileEditForm(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDto profileDto = new UserProfileDto();
        profileDto.setFullName(user.getFullName());
        profileDto.setEmail(user.getEmail());
        profileDto.setNationalId(user.getNationalId());
        profileDto.setDateOfBirth(user.getDateOfBirth());
        profileDto.setPhone(user.getPhone());
        profileDto.setAddress(user.getAddress());
        profileDto.setProvince(user.getProvince());
        profileDto.setPosition(user.getPosition());

        model.addAttribute("profileDto", profileDto);
        return "member/profile-edit-form";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("profileDto") UserProfileDto profileDto,
                              BindingResult bindingResult,
                              Principal principal,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "member/profile-edit-form"; // Quay lại form sửa nếu có lỗi
        }
        try {
            userService.updateUserProfile(principal.getName(), profileDto);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/profile";
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("passwordChangeDto", new PasswordChangeDto());
        return "member/change-password-form";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute("passwordChangeDto") PasswordChangeDto dto,
                                 Principal principal,
                                 RedirectAttributes redirectAttributes) {
        try {
            userService.changeUserPassword(principal.getName(), dto);
            redirectAttributes.addFlashAttribute("successMessage", "Đổi mật khẩu thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/profile";
    }
}