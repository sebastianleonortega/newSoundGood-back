package com.base64.gamesback.auth.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "doctor_schedule", schema = "main",
        uniqueConstraints = @UniqueConstraint(name = "doctor_schedule_date_and_time", columnNames = {"start_datetime", "end_datetime"}))
@Getter
@Setter
@NoArgsConstructor
public class DoctorSchedule {

    @Id
    @GeneratedValue
    @Column(name = "doctor_schedule")
    private UUID doctorScheduleId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_datetime", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "available", nullable = false)
    private boolean available;

    public DoctorSchedule(Doctor doctor, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.doctor = doctor;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.available = true;
    }

    public void changeAvailable(boolean available) {
        this.available = available;
    }
}
