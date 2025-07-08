package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.entity.DonationRegistration;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonationRegistrationSpecification {

    public static Specification<DonationRegistration> filterBy(
            Integer bloodTypeId,
            String phone,
            LocalDate availableDate,
            DonationRegistration.Status status) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (bloodTypeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("bloodType").get("id"), bloodTypeId));
            }
            if (phone != null && !phone.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
            }
            if (availableDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("availableDate"), availableDate));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}