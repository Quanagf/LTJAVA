// src/main/java/test/blood/donation/repositories/DonationRegistrationsRepository.java
package test.blood.donation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import test.blood.donation.model.DonationRegistrations;
import test.blood.donation.model.Users;

public interface DonationRegistrationsRepository extends JpaRepository<DonationRegistrations, Integer> {
    List<DonationRegistrations> findByUser(Users user);
}