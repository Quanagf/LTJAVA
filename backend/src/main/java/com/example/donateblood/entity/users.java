package com.example.donateblood.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
@Entity
@Table(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String full_name;
    private String username;
    private String email;
    private String password;
    private String national_id;
    private String role;
    private LocalDateTime created_at;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<blogs> blogs; 

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<donation_registrations> donation_registration; 

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<emergency_requests> emergency_request; 

    public users(){
        super();
        this.blogs = new HashSet<>();
        this.donation_registration=new HashSet<>();
        this.emergency_request=new HashSet<>();
    }
    public users(String full_name, String username, String email, String password, String national_id, String role, String created_at)
    {
        this.full_name = full_name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.national_id = national_id;
        this.role = role;
        this.created_at = LocalDateTime.now();
    }

    public Set<emergency_requests> getEmergency_request() {
        return emergency_request;
    }
    public void setEmergency_request(Set<emergency_requests> emergency_request) {
        this.emergency_request = emergency_request;
    }

    public Set<donation_registrations> getDonation_registrations() {
        return donation_registration;
    }
    public void setDonation_registrations(Set<donation_registrations> donation_registration) {
        this.donation_registration = donation_registration;
    }

    public Set<blogs> getbBlogs() {
        return blogs;
    }
    public void setBlogs(Set<blogs> blogs) {
        this.blogs = blogs;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setCreate_at(LocalDateTime create_at){
        this.created_at = create_at;
    }
    public String getFull_name(){
        return full_name;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getNational_id() {
        return national_id;
    }
    public String getRole() {
        return role;
    }
    public LocalDateTime getCreate_at() {
        return created_at;
    }
}

