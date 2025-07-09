package com.blood_donation.blood_donation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.blood_donation.blood_donation.dto.EmergencyRequestDto;
import com.blood_donation.blood_donation.entity.BloodCompatibilityRule;
import com.blood_donation.blood_donation.entity.BloodType;
import com.blood_donation.blood_donation.entity.BloodUnit;
import com.blood_donation.blood_donation.entity.EmergencyRequest;
import com.blood_donation.blood_donation.entity.MedicalCenter;
import com.blood_donation.blood_donation.entity.User;
import com.blood_donation.blood_donation.repository.BloodCompatibilityRuleRepository;
import com.blood_donation.blood_donation.repository.BloodTypeRepository;
import com.blood_donation.blood_donation.repository.BloodUnitRepository;
import com.blood_donation.blood_donation.repository.EmergencyRequestRepository;
import com.blood_donation.blood_donation.repository.MedicalCenterRepository;
import com.blood_donation.blood_donation.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EmergencyRequestServiceImpl implements EmergencyRequestService {

    @Autowired
    private EmergencyRequestRepository emergencyRequestRepository;
    @Autowired
    private BloodUnitRepository bloodUnitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BloodTypeRepository bloodTypeRepository;
    @Autowired
    private MedicalCenterRepository medicalCenterRepository;
    @Autowired
    private BloodCompatibilityRuleRepository bloodCompatibilityRuleRepository;

    @Override
    public Page<EmergencyRequest> findAllRequests(Pageable pageable) {
        return emergencyRequestRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    @Transactional
    public void approveRequest(Integer requestId) {
        EmergencyRequest request = emergencyRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu với ID: " + requestId));

        if (request.getStatus() != EmergencyRequest.Status.PENDING) {
            throw new IllegalStateException("Chỉ có thể phê duyệt yêu cầu đang ở trạng thái chờ.");
        }

        Long availableQuantity = bloodUnitRepository.getInventorySummaryByBloodType(request.getBloodType().getId());
        if (availableQuantity == null) availableQuantity = 0L;

        if (availableQuantity >= request.getQuantityNeeded()) {
            int quantityToDeduct = request.getQuantityNeeded();
            List<BloodUnit> availableUnits = bloodUnitRepository.findByBloodTypeIdAndStatusOrderByExpiryDateAsc(request.getBloodType().getId(), BloodUnit.Status.AVAILABLE);

            for (BloodUnit unit : availableUnits) {
                if (quantityToDeduct <= 0) break;
                int deductAmount = Math.min(quantityToDeduct, unit.getQuantity());
                unit.setQuantity(unit.getQuantity() - deductAmount);
                if (unit.getQuantity() == 0) {
                    unit.setStatus(BloodUnit.Status.USED);
                }
                bloodUnitRepository.save(unit);
                quantityToDeduct -= deductAmount;
            }
            request.setStatus(EmergencyRequest.Status.COMPLETED);
        } else {
            request.setStatus(EmergencyRequest.Status.PROCESSING);
        }
        emergencyRequestRepository.save(request);
    }

    @Override
    @Transactional
    public void rejectRequest(Integer requestId) {
        EmergencyRequest request = emergencyRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu với ID: " + requestId));

        if (request.getStatus() != EmergencyRequest.Status.PENDING) {
            throw new IllegalStateException("Chỉ có thể từ chối yêu cầu đang ở trạng thái chờ.");
        }
        request.setStatus(EmergencyRequest.Status.CANCELLED);
        emergencyRequestRepository.save(request);
    }

    @Override
    public void createEmergencyRequest(EmergencyRequestDto dto, String username) {
        User requester = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng."));
        BloodType bloodType = bloodTypeRepository.findById(dto.getBloodTypeId())
                .orElseThrow(() -> new RuntimeException("Nhóm máu không hợp lệ."));
        MedicalCenter center = medicalCenterRepository.findById(dto.getMedicalCenterId())
                .orElseThrow(() -> new RuntimeException("Bệnh viện không hợp lệ."));

        EmergencyRequest newRequest = new EmergencyRequest();
        newRequest.setRequesterUser(requester);
        newRequest.setPatientName(dto.getPatientName());
        newRequest.setBloodType(bloodType);
        newRequest.setQuantityNeeded(dto.getQuantityNeeded());
        newRequest.setMedicalCenter(center);
        newRequest.setPhone(dto.getPhone());
        newRequest.setReason(dto.getReason());
        newRequest.setStatus(EmergencyRequest.Status.PENDING);

        emergencyRequestRepository.save(newRequest);
    }

    @Override
    public Page<EmergencyRequest> findAllRequests(Pageable pageable, Integer bloodTypeId, String phone, EmergencyRequest.Status status) {
        Specification<EmergencyRequest> spec = EmergencyRequestSpecification.filterBy(bloodTypeId, phone, status);
        return emergencyRequestRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<EmergencyRequest> findById(Integer id) {
        return emergencyRequestRepository.findById(id);
    }

    @Override
    public List<User> findPotentialDonors(Integer recipientBloodTypeId) {
        List<BloodCompatibilityRule> compatibleRules = bloodCompatibilityRuleRepository.findAll().stream()
                .filter(rule -> rule.getRecipientBloodType().getId().equals(recipientBloodTypeId) &&
                                 rule.getComponent() == BloodCompatibilityRule.Component.RED_CELLS)
                .collect(Collectors.toList());

        List<Integer> compatibleBloodTypeIds = compatibleRules.stream()
                .map(rule -> rule.getDonorBloodType().getId())
                .collect(Collectors.toList());
        
        if (!compatibleBloodTypeIds.contains(recipientBloodTypeId)) {
            compatibleBloodTypeIds.add(recipientBloodTypeId);
        }

        return userRepository.findAll().stream()
                .filter(user -> user.getBloodType() != null && compatibleBloodTypeIds.contains(user.getBloodType().getId()))
                .collect(Collectors.toList());
    }
}