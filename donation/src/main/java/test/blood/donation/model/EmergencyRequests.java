package test.blood.donation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergency_requests")
public class EmergencyRequests {

    public enum Gender { MALE, FEMALE }
    public enum Status { PENDING, CONTACTED, COMPLETED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private BloodTypes bloodType;

    private String address;
    private String phone;
    private String province;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters, Setters, Constructors, toString...
    public EmergencyRequests() {
    }
    public EmergencyRequests(Users user, BloodTypes bloodType, String address, String phone, String province,
                              Gender gender, Status status, LocalDateTime createdAt) {
        this.user = user;
        this.bloodType = bloodType;
        this.address = address;
        this.phone = phone;
        this.province = province;
        this.gender = gender;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public BloodTypes getBloodType() {
        return bloodType;
    }
    public void setBloodType(BloodTypes bloodType) {
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
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
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
