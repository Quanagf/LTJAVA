package com.blood_donation.blood_donation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blood_donation.blood_donation.dto.BloodUnitDto;
import com.blood_donation.blood_donation.dto.BloodUnitSummaryDto;
import com.blood_donation.blood_donation.entity.BloodUnit;

public interface BloodInventoryService {
    List<BloodUnitSummaryDto> getInventorySummary();
    void addNewBloodUnit(BloodUnitDto bloodUnitDto);
    Page<BloodUnit> findAllAvailableUnits(Pageable pageable);
    void useBloodUnit(Integer unitId);
    void deleteBloodUnit(Integer unitId);
    Page<BloodUnit> findAllUnits(Pageable pageable, Integer bloodTypeId, BloodUnit.Status status);
}