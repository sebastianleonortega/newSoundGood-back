package com.base64.gamesback.auth.user.entity;

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

    @Column(name = "lastName", nullable = false)
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

    public Doctor(String name, String lastName, String phone, String address, String email, String image, String description) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
        this.description = description;
    }

    public static Doctor create(String name, String lastName, String phone, String address, String email, String image, String description) {
        return new Doctor(name, lastName, phone, address, email, image, description
        );
    }

    public void updateDoctor(String name, String lastName, String phone, String address, String email, String image, String description) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
        this.description = description;
    }

    public void addUser(User user){
        this.user = user;
    }

    public void addSpecialities(Set<Speciality> speciality){
        this.speciality = speciality;
    }
}
