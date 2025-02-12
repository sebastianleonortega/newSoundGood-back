package com.base64.gamesback.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public interface AppointmentDataResponse {

    @JsonProperty(value = "appointment_id")
    UUID getAppointmentId();

    @JsonProperty(value = "doctor_id")
    UUID getDoctorId();

    @JsonProperty(value = "speciality_id")
    UUID getSpecialityId();

    @JsonProperty(value = "speciality_name")
    String getSpecialityName();

    @JsonProperty(value = "doctor_name")
    String getDoctorName();

    @JsonProperty(value = "doctor_last_name")
    String getDoctorLastName();

    @JsonProperty(value = "doctor_phone")
    String getDoctorPhone();

    @JsonProperty(value = "doctor_image")
    String getDoctorImage();

    @JsonProperty(value = "doctor_latitude")
    String getDoctorLatitude();

    @JsonProperty(value = "doctor_longitude")
    String getDoctorLongitude();

    @JsonProperty(value = "person_name")
    String getPersonName();

    @JsonProperty(value = "person_last_name")
    String getPersonLastName();

    @JsonProperty(value = "person_phone")
    String getPersonPhone();

    @JsonProperty(value = "appointment_address")
    String getAppointmentAddress();

    @JsonProperty(value = "appointment_date")
    String getAppointmentDate();

    @JsonProperty(value = "appointment_time")
    String getAppointmentTime();
}
