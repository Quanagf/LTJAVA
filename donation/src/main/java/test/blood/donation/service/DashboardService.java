// src/main/java/test/blood/donation/service/EmergencyRequestService.java
package test.blood.donation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.blood.donation.model.BloodTypes;
import test.blood.donation.model.EmergencyRequests;
import test.blood.donation.model.Users;
import test.blood.donation.repositories.BloodTypesRepository;
import test.blood.donation.repositories.EmergencyRequestsRepository;
import test.blood.donation.repositories.UsersRepository;

@Service
public class EmergencyRequestService {

    @Autowired
    private EmergencyRequestsRepository emergencyRequestsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BloodTypesRepository bloodTypesRepository;

    public EmergencyRequests createEmergencyRequest(Integer userId, Integer bloodTypeId, String address, String phone, String province, EmergencyRequests.Gender gender) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        BloodTypes bloodType = bloodTypesRepository.findById(bloodTypeId)
                .orElseThrow(() -> new RuntimeException("Blood type not found with id: " + bloodTypeId));

        EmergencyRequests request = new EmergencyRequests();
        request.setUser(user);
        request.setBloodType(bloodType);
        request.setAddress(address);
        request.setPhone(phone);
        request.setProvince(province);
        request.setGender(gender);
        request.setStatus(EmergencyRequests.Status.PENDING);

        return emergencyRequestsRepository.save(request);
    }

    public List<EmergencyRequests> getActiveRequests() {
        return emergencyRequestsRepository.findByStatus(EmergencyRequests.Status.PENDING);
    }
}