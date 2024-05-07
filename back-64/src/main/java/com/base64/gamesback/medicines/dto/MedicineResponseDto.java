package com.base64.gamesback.medicines.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MedicineResponseDto {

    @JsonProperty(value = "medicine_id")
    private UUID medicineId;

    @JsonProperty(value = "medicine_name")
    private String medicineName;

    public MedicineResponseDto(UUID medicineId, String medicineName) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
    }
}
