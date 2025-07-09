package com.blood_donation.blood_donation.controller;

import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("loggedInUser")
    public User getLoggedInUser(Principal principal) {
        if (principal != null) {
            return userRepository.findByUsername(principal.getName()).orElse(null);
        }
        return null;
    }
}