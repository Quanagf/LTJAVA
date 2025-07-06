package com.blood_donation.blood_donation.repository;

import com.blood_donation.blood_donation.entity.BloodUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodUnitRepository extends JpaRepository<BloodUnit, Integer> {
}