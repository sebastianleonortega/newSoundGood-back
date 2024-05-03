package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserDoctorResponse {

    @NotNull
    @JsonProperty(value = "user_name")
    private String name;

    @JsonProperty(value = "administrator")
    private boolean administrator;

    @Valid
    @JsonProperty(value = "doctor")
        private DoctorDto doctor;
}
