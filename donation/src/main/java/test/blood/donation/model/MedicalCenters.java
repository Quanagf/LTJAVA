package test.blood.donation.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "medical_centers")
public class MedicalCenters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "medicalCenter", cascade = CascadeType.ALL)
    private Set<BloodUnits> bloodUnits = new HashSet<>();

    // Getters, Setters, Constructors, toString...
    public MedicalCenters() {
    }
    public MedicalCenters(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public Set<BloodUnits> getBloodUnits() {
        return bloodUnits;
    }
    public void setBloodUnits(Set<BloodUnits> bloodUnits) {
        this.bloodUnits = bloodUnits;
    }
}
