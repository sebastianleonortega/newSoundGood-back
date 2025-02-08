package com.base64.gamesback.auth.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TokenResentPasswordRequest {

    @NotBlank
    private String token;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmed;
}
