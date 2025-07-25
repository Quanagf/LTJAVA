package com.blood_donation.blood_donation.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity; // Import all from jakarta.persistence
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String username;
    private String email;
    private String password;
    private String nationalId;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String province;
    private String position;
    private boolean locked = false;

    // --- NEW FIELD ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_type_id")
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    // --- RELATIONSHIPS ---
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DonationRegistration> donationRegistrations;

    @OneToMany(mappedBy = "requesterUser", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EmergencyRequest> emergencyRequests;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Blog> blogs;
    // --- END OF RELATIONSHIPS ---

    public enum Role {
        MEMBER, STAFF, ADMIN
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}