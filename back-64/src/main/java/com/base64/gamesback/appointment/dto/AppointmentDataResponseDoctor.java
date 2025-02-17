package com.base64.gamesback.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AppointmentDataResponseDoctor {

    @JsonProperty("appointment_id")
    UUID getAppointmentId();

    @JsonProperty("appointment_status")
    String getAppointmentStatus();

    @JsonProperty("appointment_observation")
    String getAppointmentObservation();

    @JsonProperty("person_name")
    String getPersonName();

    @JsonProperty("person_last_name")
    String getPersonLastName();

    @JsonProperty("person_document")
    String getPersonDocument();

    @JsonProperty("person_phone")
    String getPersonPhone();

    @JsonProperty("person_address")
    String getPersonAddress();

    @JsonProperty("person_email")
    String getPersonEmail();

    @JsonProperty("speciality_name")
    String getSpecialityName();

    @JsonProperty("appointment_start")
    LocalDateTime getAppointmentStart();

    @JsonProperty("appointment_end")
    LocalDateTime getAppointmentEnd();
}
