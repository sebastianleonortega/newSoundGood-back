package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DoctorScheduleResponse {

    @JsonProperty(value = "doctor_schedule")
    private UUID doctorScheduleId;

    @NotNull
    @JsonProperty(value = "start_datetime")
    private LocalDateTime startDateTime;

    @NotNull
    @JsonProperty(value = "end_datetime")
    private LocalDateTime endDateTime;

    @JsonProperty(value = "available")
    private boolean available;

    public DoctorScheduleResponse(UUID doctorScheduleId, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean available) {
        this.doctorScheduleId = doctorScheduleId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.available = available;
    }
}
