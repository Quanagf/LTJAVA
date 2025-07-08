package com.blood_donation.blood_donation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.blood_donation.blood_donation.entity.BloodUnit;

@Repository
public interface BloodUnitRepository extends JpaRepository<BloodUnit, Integer>, JpaSpecificationExecutor<BloodUnit> {
    @Query("SELECT new com.blood_donation.blood_donation.dto.BloodUnitSummaryDto(bu.bloodType, SUM(bu.quantity)) " +
            "FROM BloodUnit bu WHERE bu.status = 'AVAILABLE' GROUP BY bu.bloodType")
    List<com.blood_donation.blood_donation.dto.BloodUnitSummaryDto> getInventorySummary();
    
    @Query("SELECT SUM(bu.quantity) FROM BloodUnit bu WHERE bu.bloodType.id = ?1 AND bu.status = 'AVAILABLE'")
    Long getInventorySummaryByBloodType(Integer bloodTypeId);

    List<BloodUnit> findByBloodTypeIdAndStatusOrderByExpiryDateAsc(Integer bloodTypeId, BloodUnit.Status status);

    Page<BloodUnit> findByStatus(BloodUnit.Status status, Pageable pageable);
}