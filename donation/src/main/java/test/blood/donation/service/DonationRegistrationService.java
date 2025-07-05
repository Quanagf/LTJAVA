// src/main/java/test/blood/donation/service/DonationRegistrationService.java
package test.blood.donation.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.blood.donation.model.BloodTypes;
import test.blood.donation.model.DonationRegistrations;
import test.blood.donation.model.Users;
import test.blood.donation.repositories.BloodTypesRepository;
import test.blood.donation.repositories.DonationRegistrationsRepository;
import test.blood.donation.repositories.UsersRepository;

@Service
public class DonationRegistrationService {

    @Autowired
    private DonationRegistrationsRepository donationRegistrationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BloodTypesRepository bloodTypesRepository;

    public DonationRegistrations createRegistration(Integer userId, Integer bloodTypeId, LocalDate donationDate, String address, String phone, String province, DonationRegistrations.Gender gender) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        BloodTypes bloodType = bloodTypesRepository.findById(bloodTypeId)
                .orElseThrow(() -> new RuntimeException("BloodType not found with id: " + bloodTypeId));

        DonationRegistrations registration = new DonationRegistrations();
        registration.setUser(user);
        registration.setBloodType(bloodType);
        registration.setDonationDate(donationDate);
        registration.setAddress(address);
        registration.setPhone(phone);
        registration.setProvince(province);
        registration.setGender(gender);
        registration.setStatus(DonationRegistrations.Status.PENDING);

        return donationRegistrationsRepository.save(registration);
    }

    public List<DonationRegistrations> findRegistrationsByUser(Integer userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return donationRegistrationsRepository.findByUser(user);
    }
}