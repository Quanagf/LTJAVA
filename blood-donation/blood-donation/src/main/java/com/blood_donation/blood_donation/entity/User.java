package com.blood_donation.blood_donation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

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