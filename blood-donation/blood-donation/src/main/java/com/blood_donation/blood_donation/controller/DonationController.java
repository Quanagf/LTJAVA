package com.blood_donation.blood_donation.controller;

import com.blood_donation.blood_donation.dto.DonationRegistrationDto;
import com.blood_donation.blood_donation.entity.BloodType;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/donations")
public class DonationController {

    @Autowired private DonationService donationService;
    @Autowired private BloodTypeRepository bloodTypeRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        List<BloodType> bloodTypes = bloodTypeRepository.findAll();
        
        model.addAttribute("registrationDto", new DonationRegistrationDto());
        model.addAttribute("bloodTypes", bloodTypes);
        
        return "member/donation-register-form";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("registrationDto") DonationRegistrationDto dto,
                                      Principal principal,
                                      RedirectAttributes redirectAttributes) {
        try {
            donationService.createDonationRegistration(dto, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký hiến máu thành công! Chúng tôi sẽ liên hệ với bạn sớm nhất.");
            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/donations/register";
        }
    }
}