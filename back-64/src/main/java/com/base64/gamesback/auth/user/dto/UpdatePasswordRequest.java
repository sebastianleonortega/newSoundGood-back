package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdatePasswordRequest {

    @NotBlank
    @JsonProperty(value = "user_id",required = true)
    private UUID userId;


    @NotBlank
    @JsonProperty(value = "current_password",required = true)
    private String currentPassword;

    @NotBlank
    @Size(max = 30)
    @JsonProperty(value = "password",required = true)
    private String password;

    @NotBlank
    @JsonProperty(value = "confirmation_password",required = true)
    private String confirmationPassword;
}
