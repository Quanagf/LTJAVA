package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.entity.BloodUnit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class BloodUnitSpecification {

    public static Specification<BloodUnit> filterBy(Integer bloodTypeId, BloodUnit.Status status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (bloodTypeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("bloodType").get("id"), bloodTypeId));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}