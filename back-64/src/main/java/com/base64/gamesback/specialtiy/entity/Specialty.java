package com.base64.gamesback.specialtiy.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "specialty", schema = "main")
@Getter
@NoArgsConstructor
public class Specialty {

    @Id
    @GeneratedValue
    @Column(name = "specialty_id")
    private UUID specialtyId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "speciality")
    private List<Doctor> doctor;

    public Specialty(String name) {
        this.name = name;
    }

    public void updateSpeciality(String name) {
        this.name = name;
    }

    public static Specialty create(String name) {
        return new Specialty(
                name
        );
    }

}
