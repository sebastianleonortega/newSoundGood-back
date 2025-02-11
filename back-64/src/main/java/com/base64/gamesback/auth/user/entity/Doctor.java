package com.base64.gamesback.auth.user.entity;

import com.base64.gamesback.documentType.entity.DocumentType;
import com.base64.gamesback.genderType.entity.GenderType;
import com.base64.gamesback.speciality.entity.Speciality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "doctor", schema = "main")
@Getter
@NoArgsConstructor
public class Doctor {

    @Id
    private UUID doctorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "doctor_speciality",
            schema = "main",
            joinColumns = @JoinColumn(name = "doctor_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "speciality_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"doctor_id", "speciality_id"}, name = "uc_doctor_speciality")
    )
    private Set<Speciality> speciality;

    @Column(name = "document", nullable = false, length = 15, unique = true)
    private String document;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "image", columnDefinition = "text")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "gender_type_id", nullable = false)
    private GenderType genderType;

    public Doctor(String name, String lastName, String document, String phone, String address, String email, String image, String description, String latitude, String longitude) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Doctor create(String name, String lastName, String document, String phone, String address, String email, String image, String description, String latitude, String longitude) {
        return new Doctor(name, lastName, document, phone, address, email, image, description, latitude, longitude
        );
    }

    public void updateDoctor(String name, String lastName, String document,  String phone, String address, String email, String image, String description, String latitude, String longitude) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addUser(User user){
        this.user = user;
    }

    public void addSpecialities(Set<Speciality> speciality){
        this.speciality = speciality;
    }

    public void addDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public void addGenderType(GenderType genderType) {
        this.genderType = genderType;

    }
}
