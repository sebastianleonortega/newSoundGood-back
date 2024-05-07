package com.base64.gamesback.medicines.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public interface MedicineResponse {

    @JsonProperty(value = "medicine_id")
    UUID getMedicineId();

    @JsonProperty(value = "medicine_name")
    String getMedicineName();
}
