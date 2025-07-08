package com.blood_donation.blood_donation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blood_donation.blood_donation.dto.BloodUnitDto;
import com.blood_donation.blood_donation.dto.BloodUnitSummaryDto;
import com.blood_donation.blood_donation.entity.BloodType;
import com.blood_donation.blood_donation.entity.BloodUnit;
import com.blood_donation.blood_donation.entity.MedicalCenter;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.repository.BloodUnitRepository;
import com.blood_donation.blood_donation.repository.MedicalCenterRepository;

import jakarta.transaction.Transactional;

@Service
public class BloodInventoryServiceImpl implements BloodInventoryService {
    @Autowired private BloodUnitRepository bloodUnitRepository;
    @Autowired private BloodTypeRepository bloodTypeRepository;
    @Autowired private MedicalCenterRepository medicalCenterRepository;

    @Override
    public List<BloodUnitSummaryDto> getInventorySummary() {
        return bloodUnitRepository.getInventorySummary();
    }

    @Override
    public void addNewBloodUnit(BloodUnitDto dto) {
        BloodType bloodType = bloodTypeRepository.findById(dto.getBloodTypeId())
                .orElseThrow(() -> new RuntimeException("Nhóm máu không hợp lệ."));
        MedicalCenter center = medicalCenterRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trung tâm y tế mặc định."));

        BloodUnit newUnit = new BloodUnit();
        newUnit.setBloodType(bloodType);
        newUnit.setMedicalCenter(center);
        newUnit.setQuantity(dto.getQuantity());
        newUnit.setExpiryDate(dto.getExpiryDate());
        newUnit.setStatus(BloodUnit.Status.AVAILABLE);
        bloodUnitRepository.save(newUnit);
    }

    @Override
    public Page<BloodUnit> findAllAvailableUnits(Pageable pageable) {
        return bloodUnitRepository.findByStatus(BloodUnit.Status.AVAILABLE, pageable);
    }

    @Override
    @Transactional
    public void useBloodUnit(Integer unitId) {
        BloodUnit unit = bloodUnitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn vị máu."));
        if(unit.getStatus() != BloodUnit.Status.AVAILABLE) {
            throw new IllegalStateException("Đơn vị máu này không có sẵn để sử dụng.");
        }
        unit.setQuantity(unit.getQuantity() - 1);
        if (unit.getQuantity() <= 0) {
            unit.setStatus(BloodUnit.Status.USED);
        }
        
        
        bloodUnitRepository.save(unit);
    }
    @Override
    @Transactional
    public void deleteBloodUnit(Integer unitId) {
        if (!bloodUnitRepository.existsById(unitId)) {
            throw new RuntimeException("Không tìm thấy đơn vị máu với ID: " + unitId);
        }
        bloodUnitRepository.deleteById(unitId);
    }
}