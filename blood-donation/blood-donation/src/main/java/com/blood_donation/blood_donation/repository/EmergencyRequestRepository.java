package com.blood_donation.blood_donation.repository;

import com.blood_donation.blood_donation.entity.EmergencyRequest;
import com.blood_donation.blood_donation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyRequestRepository extends JpaRepository<EmergencyRequest, Integer> {

    // Tìm các yêu cầu được tạo bởi một người dùng
    List<EmergencyRequest> findByRequesterUserOrderByCreatedAtDesc(User requesterUser);

    // Tìm các yêu cầu theo trạng thái (ví dụ: tìm các yêu cầu đang PENDING)
    List<EmergencyRequest> findByStatusOrderByCreatedAtDesc(EmergencyRequest.Status status);
}