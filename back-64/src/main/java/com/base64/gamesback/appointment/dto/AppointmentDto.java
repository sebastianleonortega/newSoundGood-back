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

    @JsonProperty(value = "speciality_id")
    private UUID speciality;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "person_id")
    private UUID personId;

    @JsonProperty(value = "doctor_id")
    private UUID doctorId;
}
