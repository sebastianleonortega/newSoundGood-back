package com.base64.gamesback.appointment.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.Person;
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
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "speciality", nullable = false)
    private String speciality;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Appointment(LocalDate date, LocalTime time, String speciality, String address) {
        this.date = date;
        this.time = time;
        this.speciality = speciality;
        this.address = address;
    }

    public static Appointment create(LocalDate date, LocalTime time, String speciality, String address){
        return new Appointment(
                date,
                time,
                speciality,
                address
        );
    }

    public void addPerson(Person person){
        this.person = person;
    }

    public void addDoctor(Doctor doctor){
        this.doctor = doctor;
    }
}
