package com.example.donateblood.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class donation_registrations {

    public enum Gender {
        MALE, FEMALE
    }

    public enum Status {
        PENDING, CONTACTED, COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private users user;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private blood_types bloodType;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String province;

    private LocalDate donationDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDate completedAt;

    private LocalDate recoveryTime;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private blood_types blood_type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private users users;



    // Constructors
    public donation_registrations() {
        super();
    }

    public donation_registrations(String address, String phone,
                                  Gender gender, String province, LocalDate donationDate,
                                  Status status, LocalDateTime createdAt, LocalDate completedAt,
                                  LocalDate recoveryTime) {
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.province = province;
        this.donationDate = donationDate;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.recoveryTime = recoveryTime;
    }

    // Getters and setters

    public users getUser() {
        return users;
    }

    public void setMedicalCenter(users users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }


    public blood_types getBloodType() {
        return bloodType;
    }

    public void setBloodType(blood_types bloodType) {
        this.bloodType = bloodType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDate completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDate getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(LocalDate recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    
}
