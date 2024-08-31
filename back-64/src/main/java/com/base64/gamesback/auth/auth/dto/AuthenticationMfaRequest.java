package com.base64.gamesback.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AuthenticationMfaRequest {

    @NotNull
    @JsonProperty(value = "user_id",required = true)
    private UUID userId;

    @NotBlank
    @JsonProperty(value = "code",required = true)
    @Size(min = 6 ,max = 6)
    private String code;

}
