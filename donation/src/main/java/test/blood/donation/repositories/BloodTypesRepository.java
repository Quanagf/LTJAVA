package test.blood.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.blood.donation.model.BloodTypes;

public interface BloodTypesRepository extends JpaRepository<BloodTypes, Integer> {
    BloodTypes findByBloodGroup(String bloodGroup);
}
