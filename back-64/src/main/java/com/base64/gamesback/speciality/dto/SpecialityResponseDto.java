package com.base64.gamesback.speciality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SpecialityResponseDto {

    @JsonProperty(value = "speciality_id")
    private UUID specialityId;

    @JsonProperty(value = "speciality_name")
    private String specialityName;

    public SpecialityResponseDto(UUID specialityId, String specialityName) {
        this.specialityId = specialityId;
        this.specialityName = specialityName;
    }
}
