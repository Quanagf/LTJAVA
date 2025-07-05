package test.blood.donation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blood_units")
public class BloodUnits {

    public enum Status { AVAILABLE, NOT_AVAILABLE }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private BloodTypes bloodType;

    @ManyToOne
    @JoinColumn(name = "medical_center_id")
    private MedicalCenters medicalCenter;

    public BloodUnits() {
    }
    public BloodUnits(int quantity, Status status, BloodTypes bloodType, MedicalCenters medicalCenter) {
        this.quantity = quantity;
        this.status = status;
        this.bloodType = bloodType;
        this.medicalCenter = medicalCenter;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public BloodTypes getBloodType() {
        return bloodType;
    }
    public void setBloodType(BloodTypes bloodType) {
        this.bloodType = bloodType;
    }
    public MedicalCenters getMedicalCenter() {
        return medicalCenter;
    }
    public void setMedicalCenter(MedicalCenters medicalCenter) {
        this.medicalCenter = medicalCenter;
    }
}
