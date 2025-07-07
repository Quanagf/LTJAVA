package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.dto.DonationRegistrationDto;
import com.blood_donation.blood_donation.entity.BloodType;
import com.blood_donation.blood_donation.entity.DonationRegistration;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.repository.DonationRegistrationRepository;
import com.blood_donation.blood_donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired private UserRepository userRepository;
    @Autowired private BloodTypeRepository bloodTypeRepository;
    @Autowired private DonationRegistrationRepository donationRegistrationRepository;

    @Override
    public void createDonationRegistration(DonationRegistrationDto dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng: " + username));

        // Kiểm tra xem user đã có đăng ký nào đang chờ chưa
        boolean hasPendingRegistration = donationRegistrationRepository
                .existsByUserAndStatus(user, DonationRegistration.Status.PENDING);

        if (hasPendingRegistration) {
            throw new RuntimeException("Bạn đã có một đăng ký hiến máu đang chờ xử lý. Vui lòng chờ hoặc liên hệ trung tâm để biết thêm chi tiết.");
        }

        BloodType bloodType = bloodTypeRepository.findById(dto.getBloodTypeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm máu với ID: " + dto.getBloodTypeId()));

        DonationRegistration registration = new DonationRegistration();
        registration.setUser(user);
        registration.setBloodType(bloodType);
        registration.setAddress(dto.getAddress());
        registration.setPhone(dto.getPhone());
        registration.setGender(dto.getGender());
        registration.setProvince(dto.getProvince());
        registration.setAvailableDate(dto.getAvailableDate());
        registration.setStatus(DonationRegistration.Status.PENDING);

        donationRegistrationRepository.save(registration);
    }
}