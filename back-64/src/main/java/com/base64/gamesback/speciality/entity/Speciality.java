package com.base64.gamesback.speciality.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "speciality", schema = "main")
@Getter
@NoArgsConstructor
public class Speciality {

    @Id
    @GeneratedValue
    @Column(name = "speciality_id")
    private UUID specialityId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "speciality")
    private List<Doctor> doctor;

    public Speciality(String name) {
        this.name = name;
    }

    public void updateSpeciality(String name) {
        this.name = name;
    }

    public static Speciality create(String name) {
        return new Speciality(
                name
        );
    }

}
