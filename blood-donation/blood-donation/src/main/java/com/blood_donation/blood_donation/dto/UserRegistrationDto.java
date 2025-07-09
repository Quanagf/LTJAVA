package com.blood_donation.blood_donation.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotBlank(message = "Họ và Tên không được để trống")
    private String fullName;
    
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;
    
    @NotBlank(message = "Email không được để trống")
    private String email;
    
    private String password;
    private String confirmPassword;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải là 10 chữ số.")
    private String phone;
    
    @NotBlank(message = "Số CMND/CCCD không được để trống")
    @Pattern(regexp = "^\\d{12}$", message = "Số CMND/CCCD phải là 12 chữ số.")
    private String nationalId;
    
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    
    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    private String province;
    
    private Integer bloodTypeId;
}