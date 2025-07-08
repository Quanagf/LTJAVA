package com.blood_donation.blood_donation.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blood_donation.blood_donation.dto.DonationEditDto;
import com.blood_donation.blood_donation.dto.DonationRegistrationDto;
import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.repository.UserRepository;
import com.blood_donation.blood_donation.service.DonationService;

@Controller
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @Autowired
    private UserRepository userRepository;

    // Hiển thị form đăng ký hiến máu
    @GetMapping("/register")
    public String showRegistrationForm(Model model, Principal principal) {
        DonationRegistrationDto dto = new DonationRegistrationDto();

        // Lấy thông tin người dùng đang đăng nhập
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy người dùng đã đăng nhập"));

        // Nếu người dùng có nhóm máu trong hồ sơ, đặt làm giá trị mặc định cho form
        if (currentUser.getBloodType() != null) {
            dto.setBloodTypeId(currentUser.getBloodType().getId());
        }
        
        // Cung cấp các thông tin cá nhân mặc định từ hồ sơ
        dto.setPhone(currentUser.getPhone());
        dto.setAddress(currentUser.getAddress());
        dto.setProvince(currentUser.getProvince());

        model.addAttribute("registrationDto", dto);
        model.addAttribute("bloodTypes", bloodTypeRepository.findAll());

        return "member/donation-register-form";
    }

    // Xử lý đăng ký hiến máu
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("registrationDto") DonationRegistrationDto dto,
                                      Principal principal,
                                      RedirectAttributes redirectAttributes) {
        try {
            donationService.createDonationRegistration(dto, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage",
                    "Đăng ký hiến máu thành công! Chúng tôi sẽ liên hệ với bạn sớm nhất.");
            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            // Giữ lại dữ liệu đã nhập trên form khi có lỗi
            redirectAttributes.addFlashAttribute("registrationDto", dto);
            return "redirect:/donations/register";
        }
    }

    @GetMapping("/history")
    public String showDonationHistory(Model model, Principal principal) {
        String username = principal.getName();
        List<DonationRegistration> history = donationService.findDonationHistoryByUsername(username);
        model.addAttribute("donationHistory", history);
        return "member/donation-history";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        Optional<DonationRegistration> registrationOpt = donationService.findRegistrationByIdAndUsername(id, principal.getName());

        if (registrationOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy hoặc không có quyền truy cập.");
            return "redirect:/donations/history";
        }

        DonationRegistration registration = registrationOpt.get();
        DonationEditDto dto = new DonationEditDto();
        dto.setId(registration.getId());
        dto.setPhone(registration.getPhone());
        dto.setAddress(registration.getAddress());
        dto.setProvince(registration.getProvince());
        dto.setAvailableDate(registration.getAvailableDate());

        model.addAttribute("editDto", dto);
        return "member/donation-edit-form";
    }

    @PostMapping("/update")
    public String processUpdate(@ModelAttribute("editDto") DonationEditDto dto, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            donationService.updateDonationRegistration(dto, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin đăng ký thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
            return "redirect:/donations/edit/" + dto.getId();
        }
        return "redirect:/donations/history";
    }
}