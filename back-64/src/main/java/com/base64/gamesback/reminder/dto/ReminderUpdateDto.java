package com.base64.gamesback.reminder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReminderUpdateDto {

    @NotNull
    @JsonProperty(value = "frequency")
    private String frequency;

    @NotNull
    @JsonProperty(value = "reminder_time")
    private String reminderTime;
}
