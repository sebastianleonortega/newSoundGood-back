package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserResponse {

    @NotNull
    @JsonProperty(value = "user_name")
    private String name;

    @JsonProperty(value = "administrator")
    private boolean administrator;

    @JsonProperty(value = "profile_image")
    private String profileImage;

    @Valid
    @JsonProperty(value = "person")
    private PersonDto person;
}
