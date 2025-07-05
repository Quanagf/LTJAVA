package test.blood.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.blood.donation.model.DonationRegistrations;

import java.util.List;

public interface DonationRegistrationsRepository extends JpaRepository<DonationRegistrations, Integer> {
    List<DonationRegistrations> findByUserId(int userId);
    List<DonationRegistrations> findByStatus(DonationRegistrations.Status status);
}
