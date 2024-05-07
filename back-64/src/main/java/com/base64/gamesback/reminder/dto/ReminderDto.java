package com.base64.gamesback.reminder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReminderDto {

    @NotNull
    @JsonProperty(value = "person_id")
    private UUID personId;

    @NotNull
    @JsonProperty(value = "medicine_id")
    private UUID medicineId;

    @NotNull
    @JsonProperty(value = "frequency")
    private String frequency;

    @NotNull
    @JsonProperty(value = "reminder_time")
    private String reminderTime;
}
