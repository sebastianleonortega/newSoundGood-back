package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UserResponseDto {

    @JsonProperty(value = "user_id")
    private UUID userId;

    @JsonProperty(value = "user_name")
    private String name;

    @JsonProperty(value = "user_email")
    private String userEmail;

    @JsonProperty(value = "last_login")
    private LocalDateTime lastLogin;

    @JsonProperty(value = "status")
    private String status;

    public UserResponseDto(UUID userId, String name, String userEmail, LocalDateTime lastLogin, String status) {
        this.userId = userId;
        this.name = name;
        this.userEmail = userEmail;
        this.lastLogin = lastLogin;
        this.status = status;
    }
}
