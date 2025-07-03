package com.example.donateblood.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class emergency_requests {

    public enum Gender {
        MALE, FEMALE, OTHER
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

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public emergency_requests() {}

    public emergency_requests(users user, blood_types bloodType, String address, String phone,
                             Gender gender, String province, Status status, LocalDateTime createdAt) {
        this.user = user;
        this.bloodType = bloodType;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.province = province;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
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
}

