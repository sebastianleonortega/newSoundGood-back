package com.base64.gamesback.specialtiy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public interface SpecialityResponse {

    @JsonProperty(value = "speciality_id")
    UUID getSpecialityId();

    @JsonProperty(value = "speciality_name")
    String getSpecialityName();
}
