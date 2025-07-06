package com.blood_donation.blood_donation.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}