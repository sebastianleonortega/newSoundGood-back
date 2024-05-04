package com.base64.gamesback.genderType.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GenderTypeResponse {

    @JsonProperty(value = "gender_type_id")
    private UUID genderId;

    @JsonProperty(value = "code")
    private String code;

    public GenderTypeResponse(UUID genderId, String code) {
        this.genderId = genderId;
        this.code = code;
    }
}
