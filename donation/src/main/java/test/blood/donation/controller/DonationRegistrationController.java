// src/main/java/test/blood/donation/controller/DonationRegistrationController.java
package test.blood.donation.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.blood.donation.model.DonationRegistrations;
import test.blood.donation.service.DonationRegistrationService;

@RestController
@RequestMapping("/api/registrations")
public class DonationRegistrationController {

    @Autowired
    private DonationRegistrationService donationRegistrationService;

    @PostMapping
    public ResponseEntity<DonationRegistrations> createRegistration(@RequestBody Map<String, Object> payload) {
        Integer userId = Integer.parseInt(payload.get("userId").toString());
        Integer bloodTypeId = Integer.parseInt(payload.get("bloodTypeId").toString());
        LocalDate donationDate = LocalDate.parse(payload.get("donationDate").toString());
        String address = payload.get("address").toString();
        String phone = payload.get("phone").toString();
        String province = payload.get("province").toString();
        DonationRegistrations.Gender gender = DonationRegistrations.Gender.valueOf(payload.get("gender").toString().toUpperCase());

        DonationRegistrations newRegistration = donationRegistrationService.createRegistration(userId, bloodTypeId, donationDate, address, phone, province, gender);
        return ResponseEntity.ok(newRegistration);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DonationRegistrations>> getRegistrationsByUser(@PathVariable Integer userId) {
        List<DonationRegistrations> registrations = donationRegistrationService.findRegistrationsByUser(userId);
        return ResponseEntity.ok(registrations);
    }
}