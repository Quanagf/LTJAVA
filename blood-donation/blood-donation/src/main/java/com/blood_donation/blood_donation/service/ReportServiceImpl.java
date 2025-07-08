package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.EmergencyRequest;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.DonationRegistrationRepository;
import com.blood_donation.blood_donation.repository.EmergencyRequestRepository;
import com.blood_donation.blood_donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.EnumMap;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired private UserRepository userRepository;
    @Autowired private DonationRegistrationRepository donationRegistrationRepository;
    @Autowired private EmergencyRequestRepository emergencyRequestRepository;

    @Override
    public Map<User.Role, Long> countUsersByRole() {
        Map<User.Role, Long> userCounts = new EnumMap<>(User.Role.class);
        for (User.Role role : User.Role.values()) {
            userCounts.put(role, userRepository.countByRole(role));
        }
        return userCounts;
    }

    @Override
    public long countCompletedDonations() {
        return donationRegistrationRepository.countByStatus(DonationRegistration.Status.COMPLETED);
    }

    @Override
    public long countCompletedDonationsThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);
        return donationRegistrationRepository.countByStatusAndCompletedAtBetween(DonationRegistration.Status.COMPLETED, startOfMonth, endOfMonth);
    }

    @Override
    public long countPendingEmergencyRequests() {
        return emergencyRequestRepository.countByStatus(EmergencyRequest.Status.PENDING);
    }
}