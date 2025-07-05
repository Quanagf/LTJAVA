package test.blood.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.blood.donation.model.EmergencyRequests;

import java.util.List;

public interface EmergencyRequestsRepository extends JpaRepository<EmergencyRequests, Integer> {
    List<EmergencyRequests> findByStatus(EmergencyRequests.Status status);
    List<EmergencyRequests> findByProvince(String province);
}
