package com.blood_donation.blood_donation.service;

import java.util.Map;
import com.blood_donation.blood_donation.entity.User;

public interface ReportService {
    Map<User.Role, Long> countUsersByRole();
    long countCompletedDonations();
    long countCompletedDonationsThisMonth();
    long countPendingEmergencyRequests();
}