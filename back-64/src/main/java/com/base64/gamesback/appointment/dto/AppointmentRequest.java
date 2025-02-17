package com.base64.gamesback.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AppointmentRequest {

    @NotNull
    @JsonProperty(value = "speciality_id")
    private UUID speciality;

    @NotNull
    @JsonProperty(value = "person_id")
    private UUID personId;

    @NotNull
    @JsonProperty(value = "doctor_id")
    private UUID doctorId;

    @NotNull
    @JsonProperty(value = "doctor_schedule_id")
    private UUID doctorScheduleId;
}
