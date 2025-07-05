package test.blood.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.blood.donation.model.MedicalCenters;

public interface MedicalCentersRepository extends JpaRepository<MedicalCenters, Integer> {
}
