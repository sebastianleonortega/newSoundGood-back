package com.base64.gamesback.medicines.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MedicineDto {

    @NotNull
    @JsonProperty(value = "medicine_name")
    private String medicineName;
}
