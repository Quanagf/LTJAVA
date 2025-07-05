// src/main/java/test/blood/donation/controller/EmergencyRequestController.java
package test.blood.donation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.blood.donation.model.EmergencyRequests;
import test.blood.donation.service.EmergencyRequestService;

@RestController
@RequestMapping("/api/emergencies")
public class EmergencyRequestController {

    @Autowired
    private EmergencyRequestService emergencyRequestService;

    @PostMapping
    public ResponseEntity<EmergencyRequests> createEmergencyRequest(@RequestBody Map<String, Object> payload) {
        Integer userId = Integer.parseInt(payload.get("userId").toString());
        Integer bloodTypeId = Integer.parseInt(payload.get("bloodTypeId").toString());
        String address = payload.get("address").toString();
        String phone = payload.get("phone").toString();
        String province = payload.get("province").toString();
        EmergencyRequests.Gender gender = EmergencyRequests.Gender.valueOf(payload.get("gender").toString().toUpperCase());

        EmergencyRequests newRequest = emergencyRequestService.createEmergencyRequest(userId, bloodTypeId, address, phone, province, gender);
        return ResponseEntity.ok(newRequest);
    }

    @GetMapping
    public ResponseEntity<List<EmergencyRequests>> getActiveRequests() {
        List<EmergencyRequests> requests = emergencyRequestService.getActiveRequests();
        return ResponseEntity.ok(requests);
    }
}