package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDoctorUpdateRequest {

    @NotNull
    @NotEmpty
    @JsonProperty(value = "user_name")
    private String userName;

    @Valid
    @JsonProperty(value = "doctor")
    private DoctorUpdateRequest doctor;
}
