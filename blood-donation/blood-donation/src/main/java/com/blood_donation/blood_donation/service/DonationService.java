package com.blood_donation.blood_donation.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.blood_donation.blood_donation.dto.DonationEditDto;
import com.blood_donation.blood_donation.dto.DonationRegistrationDto;
import com.blood_donation.blood_donation.entity.DonationRegistration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonationService {
    /**
     * Tạo một lượt đăng ký hiến máu mới cho người dùng đang đăng nhập.
     * Sẽ báo lỗi nếu người dùng đã có đăng ký đang chờ.
     * @param dto Dữ liệu từ form
     * @param username Tên đăng nhập của người dùng hiện tại
     */
    void createDonationRegistration(DonationRegistrationDto dto, String username);
    List<DonationRegistration> findDonationHistoryByUsername(String username);
    Optional<DonationRegistration> findRegistrationByIdAndUsername(Integer id, String username);
    void updateDonationRegistration(DonationEditDto dto, String username);
    Page<DonationRegistration> findAllRegistrations(Pageable pageable, Integer bloodTypeId, String phone, LocalDate availableDate, DonationRegistration.Status status);
}