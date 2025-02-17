package com.base64.gamesback.auth.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "doctor_schedule", schema = "main",
        uniqueConstraints = @UniqueConstraint(name = "doctor_schedule_date", columnNames = {"start_date", "end_date"}))
@Getter
@Setter
@NoArgsConstructor
public class DoctorSchedule {

    @Id
    @GeneratedValue
    @Column(name = "doctor_schedule_id")
    private UUID doctorScheduleId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "available", nullable = false)
    private boolean available;

    public DoctorSchedule(Doctor doctor, LocalDateTime startDate, LocalDateTime endDate) {
        this.doctor = doctor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.available = true;
    }

    public void changeAvailable(boolean available) {
        this.available = available;
    }
}
