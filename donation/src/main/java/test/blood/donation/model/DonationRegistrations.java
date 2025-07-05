package test.blood.donation.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "donation_registrations")
public class DonationRegistrations {

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
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "blood_type_id", nullable = false)
    private BloodTypes bloodType;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String province;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate donationDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDateTime createdAt;

    private LocalDate completedAt;

    private LocalDate recoveryTime;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public DonationRegistrations() {}

    public DonationRegistrations(Users user, BloodTypes bloodType, String address, String phone, String province,
                                  Gender gender, LocalDate donationDate, Status status,
                                  LocalDate completedAt, LocalDate recoveryTime) {
        this.user = user;
        this.bloodType = bloodType;
        this.address = address;
        this.phone = phone;
        this.province = province;
        this.gender = gender;
        this.donationDate = donationDate;
        this.status = status;
        this.completedAt = completedAt;
        this.recoveryTime = recoveryTime;
    }

    // --- Getters & Setters ---

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "DonationRegistrations{" +
                "id=" + id +
                ", user=" + (user != null ? user.getUsername() : "N/A") +
                ", bloodType=" + (bloodType != null ? bloodType.getBloodGroup() : "N/A") +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", gender=" + gender +
                ", donationDate=" + donationDate +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", completedAt=" + completedAt +
                ", recoveryTime=" + recoveryTime +
                '}';
    }
}
