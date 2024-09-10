package com.base64.gamesback.prescription.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PrescriptionDto {

    @NotNull
    @JsonProperty(value = "person_id", required = true)
    private UUID personId;

    @NotNull
    @JsonProperty(value = "doctor_id", required = true)
    private UUID doctorId;

    @NotNull
    @JsonProperty(value = "description", required = true)
    private String description;

    @NotNull
    @JsonProperty(value = "recommendation", required = true)
    private String recommendation;

    @JsonProperty(value = "medicines", required = true)
    @Size(min = 1)
    private UUID[] medicines;
}
