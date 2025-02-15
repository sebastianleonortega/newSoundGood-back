package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateStatusUserRequest {

    @NotBlank
    @JsonProperty(value = "admin_user_id",required = true)
    private UUID AdminUserId;

    @NotBlank
    @JsonProperty(value = "user_id",required = true)
    private UUID userId;

    @NotBlank
    @JsonProperty(value = "status_user", required = true)
    private String status;
}
