package test.blood.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.blood.donation.model.BloodUnits;
import test.blood.donation.model.BloodUnits.Status;

import java.util.List;

public interface BloodUnitsRepository extends JpaRepository<BloodUnits, Integer> {
    List<BloodUnits> findByStatus(Status status);
    List<BloodUnits> findByMedicalCenterId(int centerId);
}
