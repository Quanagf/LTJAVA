package com.blood_donation.blood_donation.dto;

import java.time.LocalDate;

import com.blood_donation.blood_donation.entity.User;

import lombok.Data;

@Data
public class AdminUserEditDto {
    private Integer id;
    private String fullName;
    private String email;
    private User.Role role;
    private String username;
    private String phone; // <-- ADDED
    private String nationalId; // <-- ADDED
    private String address; // <-- ADDED
    private String province; // <-- ADDED
    private LocalDate dateOfBirth; // <-- ADDED
    private String position; // <-- ADDED
}