package com.blood_donation.blood_donation.dto;

import com.blood_donation.blood_donation.entity.DonationRegistration;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class DonationRegistrationDto {
    private Integer bloodTypeId;
    private String address;
    private String phone;
    private DonationRegistration.Gender gender;
    private String province;

    // Giúp Spring Boot hiểu định dạng ngày tháng từ form HTML
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate availableDate;
}