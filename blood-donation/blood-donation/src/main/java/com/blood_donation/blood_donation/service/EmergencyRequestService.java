package com.blood_donation.blood_donation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blood_donation.blood_donation.dto.EmergencyRequestDto;
import com.blood_donation.blood_donation.entity.EmergencyRequest;
import com.blood_donation.blood_donation.entity.User;

public interface EmergencyRequestService {
    Page<EmergencyRequest> findAllRequests(Pageable pageable);
    void approveRequest(Integer requestId);
    void rejectRequest(Integer requestId);
    void createEmergencyRequest(EmergencyRequestDto dto, String username);
    Page<EmergencyRequest> findAllRequests(Pageable pageable, Integer bloodTypeId, String phone, EmergencyRequest.Status status);
    Optional<EmergencyRequest> findById(Integer id);
    List<User> findPotentialDonors(Integer recipientBloodTypeId);
}