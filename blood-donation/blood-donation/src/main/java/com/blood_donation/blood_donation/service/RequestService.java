package com.blood_donation.blood_donation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.EmergencyRequest;

public interface RequestService {
    Page<EmergencyRequest> findPendingEmergencyRequests(Pageable pageable);
    Page<DonationRegistration> findPendingDonationRegistrations(Pageable pageable);
    void approveDonationRegistration(Integer registrationId);
    void rejectDonationRegistration(Integer registrationId);

    // Đã phục hồi 2 phương thức này
    Page<DonationRegistration> findApprovedDonationRegistrations(Pageable pageable);
    Page<DonationRegistration> findContactedDonationRegistrations(Pageable pageable);

    void contactDonationRegistration(Integer registrationId);
    void completeDonation(Integer registrationId);
}