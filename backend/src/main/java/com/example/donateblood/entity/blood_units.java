package com.example.donateblood.entity;

import jakarta.persistence.*;

@Entity
public class blood_units {

    public enum Status {
        POSITIVE,
        NEGATIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private blood_types blood_type;

    @ManyToOne
    @JoinColumn(name = "medical_center_id")
    private medical_centers medical_centers;

    // Constructor mặc định
    public blood_units() {
        super();
    }

    // Constructor đầy đủ
    public blood_units(int quantity, Status status) {
        this.quantity = quantity;
        this.status = status;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public blood_types getBloodType() {
        return blood_type;
    }

    public void setBloodType(blood_types blood_type) {
        this.blood_type = blood_type;
    }
    public medical_centers getMedicalCenter() {
        return medical_centers;
    }

    public void setMedicalCenter(medical_centers medical_centers) {
        this.medical_centers = medical_centers;
    }
}
