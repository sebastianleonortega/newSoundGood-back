package com.base64.gamesback.specialtiy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SpecialityResponseDto {

    @JsonProperty(value = "speciality_id")
    private String specialityId;

    @JsonProperty(value = "speciality_name")
    private String specialityName;
}
