package com.example.donateblood.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class blood_types {

    public enum Rhfactor {
        POSITIVE,
        NEGATIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String blood_group;
    private String transfusion_method;
    private String compatible_with;

    @Enumerated(EnumType.STRING)
    private Rhfactor rhfactor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blood_type_id")
    private Set<blood_units> bloodUnits;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blood_type_id")
    private Set<donation_registrations> donation_registration; 

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blood_type_id")
    private Set<donation_registrations> emergency_request; 

    // Constructor mặc định
    public blood_types() {
        super();
        this.bloodUnits = new HashSet<>();
        this.donation_registration=new HashSet<>();
        this.emergency_request=new HashSet<>();
    }

    // Constructor đầy đủ
    public blood_types(String blood_group, String transfusion_method, String compatible_with, Rhfactor rhfactor) {
        this.blood_group = blood_group;
        this.transfusion_method = transfusion_method;
        this.compatible_with = compatible_with;
        this.rhfactor = rhfactor;
    }

    // Getters và Setters

    public Set<donation_registrations> getDonation_registrations() {
        return donation_registration;
    }
    public void setDonation_registrations(Set<donation_registrations> donation_registration) {
        this.donation_registration = donation_registration;
    }

    public void setBloodUnits(Set<blood_units> bloodUnits){
        this.bloodUnits = bloodUnits;
    }

    public Set<blood_units> getBloodUnits(){
        return bloodUnits;
    }

    public int getId() {
        return id;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getTransfusion_method() {
        return transfusion_method;
    }

    public void setTransfusion_method(String transfusion_method) {
        this.transfusion_method = transfusion_method;
    }

    public String getCompatible_with() {
        return compatible_with;
    }

    public void setCompatible_with(String compatible_with) {
        this.compatible_with = compatible_with;
    }

    public Rhfactor getRhfactor() {
        return rhfactor;
    }

    public void setRhfactor(Rhfactor rhfactor) {
        this.rhfactor = rhfactor;
    }
}

