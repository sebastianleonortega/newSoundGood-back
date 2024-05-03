package com.base64.gamesback.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {


    @NotNull
    @NotEmpty
    @JsonProperty(value = "user_name")
    private String name;


    @NotNull
    @NotEmpty
    @JsonProperty(value = "password")
    private String password;
}
