package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DoctorScheduleUpdateRequest {

    @NotNull
    @JsonProperty(value = "doctor_schedule_id")
    private UUID doctorScheduleId;

    @NotNull
    @JsonProperty(value = "available")
    private boolean available;

    public DoctorScheduleUpdateRequest(UUID doctorScheduleId, boolean available) {
        this.doctorScheduleId = doctorScheduleId;
        this.available = available;
    }
}
