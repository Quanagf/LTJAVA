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
    private String phone;
    private String nationalId;
    private String address;
    private String province;
    private LocalDate dateOfBirth;
    private String position;
    private Integer bloodTypeId; // <-- ADDED
}