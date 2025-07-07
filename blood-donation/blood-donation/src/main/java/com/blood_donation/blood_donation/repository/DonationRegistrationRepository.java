package com.blood_donation.blood_donation.repository;

import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface DonationRegistrationRepository extends JpaRepository<DonationRegistration, Integer> {

    // Lấy danh sách đăng ký của 1 người dùng, mới nhất lên đầu
    List<DonationRegistration> findByUserOrderByCreatedAtDesc(User user);

    // Đếm số lượt đăng ký theo trạng thái
    long countByStatus(DonationRegistration.Status status);

    // Lấy top 5 lượt đăng ký gần nhất theo trạng thái
    List<DonationRegistration> findTop5ByStatusOrderByCreatedAtDesc(DonationRegistration.Status status);

    // Phân trang danh sách đăng ký theo trạng thái
    Page<DonationRegistration> findByStatusOrderByCreatedAtDesc(DonationRegistration.Status status, Pageable pageable);

    // Kiểm tra người dùng đã có đăng ký với trạng thái cụ thể chưa
    boolean existsByUserAndStatus(User user, DonationRegistration.Status status);
}
