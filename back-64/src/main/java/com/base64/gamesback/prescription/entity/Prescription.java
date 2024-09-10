package com.base64.gamesback.prescription.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.medicines.entity.Medicine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "prescription", schema = "main")
@Getter
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue
    @Column(name = "prescription")
    private UUID prescriptionId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "recommendation", nullable = false, columnDefinition = "TEXT")
    private String recommendation;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "prescription_medicine",
            schema = "main",
            joinColumns = @JoinColumn(name = "prescription_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "medicine_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"prescription_id", "medicine_id"}, name = "uc_prescription_medicine")
    )
    private Set<Medicine> medicines;

    @CreationTimestamp
    @Column(name = "prescription_create_date", nullable = false)
    private LocalDateTime prescriptionCreationDate;

    @UpdateTimestamp
    @Column(name = "prescription_update_date", nullable = false)
    private LocalDateTime prescriptionUpdateDate;

    public Prescription(String description, String recommendation) {
        this.description = description;
        this.recommendation = recommendation;
    }

    public static Prescription create(String description, String recommendation){
        return new Prescription(
          description, recommendation
        );
    }

    public void addPerson(Person person){
        this.person = person;
    }

    public void addDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public void addMedicines(Set<Medicine> medicines){
        this.medicines = medicines;
    }
}
