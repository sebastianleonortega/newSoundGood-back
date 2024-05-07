package com.base64.gamesback.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
public class AppointmentDto {

    @JsonProperty(value = "date")
    private String date;

    @JsonProperty(value = "time")
    private String time;

    @JsonProperty(value = "speciality")
    private String speciality;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "user_id")
    private UUID userId;

    @JsonProperty(value = "doctor_id")
    private UUID doctorId;
}
