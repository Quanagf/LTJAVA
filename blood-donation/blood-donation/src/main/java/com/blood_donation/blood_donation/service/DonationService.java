package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.dto.DonationRegistrationDto;

public interface DonationService {
    /**
     * Tạo một lượt đăng ký hiến máu mới cho người dùng đang đăng nhập.
     * Sẽ báo lỗi nếu người dùng đã có đăng ký đang chờ.
     * @param dto Dữ liệu từ form
     * @param username Tên đăng nhập của người dùng hiện tại
     */
    void createDonationRegistration(DonationRegistrationDto dto, String username);
}