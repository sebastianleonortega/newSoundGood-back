package com.base64.gamesback.appointment.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.speciality.entity.Speciality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "appointment", schema = "main")
@Getter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue
    @Column(name = "appointment_id")
    private UUID appointmentId;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    public Appointment(String date, String time, String address) {
        this.date = date;
        this.time = time;
        this.address = address;
    }

    public static Appointment create(String date, String time, String address){
        return new Appointment(
                date,
                time,
                address
        );
    }

    public void addPerson(Person person){
        this.person = person;
    }

    public void addDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public void addSpeciality(Speciality speciality){
        this.speciality = speciality;
    }
}
