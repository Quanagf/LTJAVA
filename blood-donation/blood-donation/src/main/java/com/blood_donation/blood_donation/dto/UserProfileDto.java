package com.blood_donation.blood_donation.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserProfileDto {
    @NotBlank(message = "Họ và Tên không được để trống")
    private String fullName;
    
    @NotBlank(message = "Email không được để trống")
    private String email;
    
    @NotBlank(message = "Số CMND/CCCD không được để trống")
    @Pattern(regexp = "^\\d{12}$", message = "Số CMND/CCCD phải là 12 chữ số.")
    private String nationalId;
    
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải là 10 chữ số.")
    private String phone;
    
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    
    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    private String province;
    
    private String position;
}