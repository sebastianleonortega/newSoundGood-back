package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DoctorScheduleResponse {

    @JsonProperty(value = "doctor_schedule_id")
    private UUID doctorScheduleId;

    @JsonProperty(value = "start_date")
    private LocalDateTime startDate;

    @JsonProperty(value = "end_date")
    private LocalDateTime endDate;

    @JsonProperty(value = "available")
    private boolean available;

    public DoctorScheduleResponse(UUID doctorScheduleId, LocalDateTime startDate, LocalDateTime endDate, boolean available) {
        this.doctorScheduleId = doctorScheduleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.available = available;
    }
}
