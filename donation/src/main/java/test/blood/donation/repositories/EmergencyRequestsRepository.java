// src/main/java/test/blood/donation/repositories/EmergencyRequestsRepository.java
package test.blood.donation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import test.blood.donation.model.EmergencyRequests;

public interface EmergencyRequestsRepository extends JpaRepository<EmergencyRequests, Integer> {
    List<EmergencyRequests> findByStatus(EmergencyRequests.Status status);
}