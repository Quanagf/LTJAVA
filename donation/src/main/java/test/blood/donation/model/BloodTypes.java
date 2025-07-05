package test.blood.donation.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "blood_types")
public class BloodTypes {

    public enum Rhfactor { POSITIVE, NEGATIVE }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bloodGroup;
    private String transfusionMethod;
    private String compatibleWith;

    @Enumerated(EnumType.STRING)
    private Rhfactor rhfactor;

    @OneToMany(mappedBy = "bloodType", cascade = CascadeType.ALL)
    private Set<BloodUnits> bloodUnits = new HashSet<>();

    @OneToMany(mappedBy = "bloodType", cascade = CascadeType.ALL)
    private Set<DonationRegistrations> donationRegistrations = new HashSet<>();

    @OneToMany(mappedBy = "bloodType", cascade = CascadeType.ALL)
    private Set<EmergencyRequests> emergencyRequests = new HashSet<>();

    public BloodTypes() {
    }
    public BloodTypes(String bloodGroup, String transfusionMethod, String compatibleWith, Rhfactor rhfactor) {
        this.bloodGroup = bloodGroup;
        this.transfusionMethod = transfusionMethod;
        this.compatibleWith = compatibleWith;
        this.rhfactor = rhfactor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getTransfusionMethod() {
        return transfusionMethod;
    }
    public void setTransfusionMethod(String transfusionMethod) {
        this.transfusionMethod = transfusionMethod;
    }
    public String getCompatibleWith() {
        return compatibleWith;
    }
    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }   

    public Rhfactor getRhfactor() {
        return rhfactor;
    }
    public void setRhfactor(Rhfactor rhfactor) {
        this.rhfactor = rhfactor;
    }
    public Set<BloodUnits> getBloodUnits() {
        return bloodUnits;
    }
    public void setBloodUnits(Set<BloodUnits> bloodUnits) {
        this.bloodUnits = bloodUnits;
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
