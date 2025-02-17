package com.base64.gamesback.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AppointmentUpdateRequest {

    @NotNull
    @JsonProperty(value = "appointment_id")
    private UUID appointmentId;

    @NotBlank
    @JsonProperty(value = "appointment_status")
    private String appointmentStatus;

    @NotBlank
    @JsonProperty(value = "appointment_observation")
    private String appointmentObservation;
}
