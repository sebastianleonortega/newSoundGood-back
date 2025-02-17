package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DoctorScheduleRequest {

    @NotNull
    @JsonProperty(value = "start_date")
    private LocalDateTime startDate;

    @NotNull
    @JsonProperty(value = "end_date")
    private LocalDateTime endDate;
}
