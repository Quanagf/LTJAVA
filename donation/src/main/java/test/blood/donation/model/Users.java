package test.blood.donation.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String username;
    private String email;
    private String password;
    private String nationalId;
    private String role;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Blogs> blogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<DonationRegistrations> donationRegistrations = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<EmergencyRequests> emergencyRequests = new HashSet<>();

    // Getters, Setters, Constructors, toString...
    public Users() {
    }
    public Users(String fullName, String username, String email, String password, String nationalId, String role) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nationalId = nationalId;
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNationalId() {
        return nationalId;
    }
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Set<Blogs> getBlogs() {
        return blogs;
    }
    public void setBlogs(Set<Blogs> blogs) {
        this.blogs = blogs;
    }
    public Set<DonationRegistrations> getDonationRegistrations() {
        return donationRegistrations;
    }
    public void setDonationRegistrations(Set<DonationRegistrations> donationRegistrations) {
        this.donationRegistrations = donationRegistrations;
    }
    public Set<EmergencyRequests> getEmergencyRequests() {
        return emergencyRequests;
    }
    public void setEmergencyRequests(Set<EmergencyRequests> emergencyRequests) {
        this.emergencyRequests = emergencyRequests;
    }

}