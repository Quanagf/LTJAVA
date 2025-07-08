package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.entity.EmergencyRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class EmergencyRequestSpecification {

    public static Specification<EmergencyRequest> filterBy(
            Integer bloodTypeId,
            String phone,
            EmergencyRequest.Status status) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (bloodTypeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("bloodType").get("id"), bloodTypeId));
            }
            if (phone != null && !phone.isEmpty()) {
                // Tìm kiếm gần đúng với số điện thoại
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}