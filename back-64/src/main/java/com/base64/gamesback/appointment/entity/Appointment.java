package com.base64.gamesback.appointment.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.speciality.entity.Speciality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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

    @Column(name = "appointment_status", nullable = false)
    private String appointmentStatus;

    @Column(name = "appointment_observation", nullable = false)
    private String appointmentObservation;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "doctor_schedule_id")
    private DoctorSchedule doctorSchedule;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    public Appointment(Person person, Doctor doctor, Speciality speciality, DoctorSchedule doctorSchedule) {
        this.person = person;
        this.doctor = doctor;
        this.speciality = speciality;
        this.doctorSchedule = doctorSchedule;
        this.appointmentStatus = "CONFIRMADA";
        this.appointmentObservation = "Sin observaciones";
    }

    public static Appointment create(Person person, Doctor doctor, Speciality speciality, DoctorSchedule doctorSchedule) {
        return new Appointment(
                person,
                doctor,
                speciality,
                doctorSchedule
        );
    }

    public void updateStatusAndObservation(String appointmentStatus, String appointmentObservation) {
        this.appointmentStatus = appointmentStatus;
        this.appointmentObservation = appointmentObservation;
    }
}
