package com.base64.gamesback.reminder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public interface ReminderResponse {

    @JsonProperty(value = "reminder_id")
     UUID getReminderId();

    @JsonProperty(value = "reminder_frequency")
    String getReminderFrequency();

    @JsonProperty(value = "reminder_time")
     String getReminderTime();

    @JsonProperty(value = "medicine_name")
     String getMedicineName();

    @JsonProperty(value = "person_name")
     String getPersonName();

    @JsonProperty(value = "person_last_name")
     String getPersonLastName();
}
