package com.example.donateblood.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class medical_centers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_center_id")
    private Set<blood_units> bloodUnits;
    // Constructors
    public medical_centers() {
        super();
        this.bloodUnits = new HashSet<>();
    }

    public medical_centers(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    // Getters & Setters

    public void setBloodUnits(Set<blood_units> bloodUnits){
        this.bloodUnits = bloodUnits;
    }

    public Set<blood_units> getBloodUnits(){
        return bloodUnits;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

