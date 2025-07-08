package com.blood_donation.blood_donation.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.blood_donation.blood_donation.entity.BloodType; // Thêm import
import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.User;

@Repository
public interface DonationRegistrationRepository extends JpaRepository<DonationRegistration, Integer>, JpaSpecificationExecutor<DonationRegistration> {

    List<DonationRegistration> findByUserOrderByCreatedAtDesc(User user);
    boolean existsByUserAndStatus(User user, DonationRegistration.Status status);
    Page<DonationRegistration> findByStatusOrderByCreatedAtDesc(DonationRegistration.Status status, Pageable pageable);
    Optional<DonationRegistration> findTopByUserAndStatusOrderByCompletedAtDesc(User user, DonationRegistration.Status status);
    boolean existsByUserAndStatusIn(User user, List<DonationRegistration.Status> statuses);
    List<DonationRegistration> findTop5ByStatusOrderByCreatedAtDesc(DonationRegistration.Status status);
    long countByStatus(DonationRegistration.Status status);
    long countByStatusAndCompletedAtBetween(DonationRegistration.Status status, LocalDateTime start, LocalDateTime end);
    
    // <-- ADDED METHOD -->
    List<DonationRegistration> findByStatusAndBloodType(DonationRegistration.Status status, BloodType bloodType);
}