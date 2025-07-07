package com.blood_donation.blood_donation.repository;

import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRegistrationRepository extends JpaRepository<DonationRegistration, Integer> {

    // Tìm tất cả các lượt đăng ký hiến máu của một người dùng cụ thể, sắp xếp theo ngày tạo mới nhất
    List<DonationRegistration> findByUserOrderByCreatedAtDesc(User user);
    boolean existsByUserAndStatus(User user, DonationRegistration.Status status);

}