package com.base64.gamesback.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AppointmentDataResponsePatient {

    @JsonProperty("appointment_id")
    UUID getAppointmentId();

    @JsonProperty("appointment_status")
    String getAppointmentStatus();

    @JsonProperty("appointment_observation")
    String getAppointmentObservation();

    @JsonProperty("doctor_name")
    String getDoctorName();

    @JsonProperty("doctor_address")
    String getDoctorAddress();

    @JsonProperty("doctor_phone")
    String getDoctorPhone();

    @JsonProperty("doctor_email")
    String getDoctorEmail();

    @JsonProperty("doctor_image")
    String getDoctorImage();

    @JsonProperty("speciality_name")
    String getSpecialityName();

    @JsonProperty("appointment_start")
    LocalDateTime getAppointmentStart();

    @JsonProperty("appointment_end")
    LocalDateTime getAppointmentEnd();
}
