package com.base64.gamesback.speciality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SpecialityDto {

    @NotNull
    @JsonProperty(value = "speciality_name")
    private String specialityName;
}
