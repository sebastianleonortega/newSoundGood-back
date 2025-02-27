package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TestRequest {

    @NotNull
    @JsonProperty(value = "person_id", required = true)
    private UUID personId;

    @NotBlank
    @JsonProperty(value = "test_type", required = true)
    private String testType;

    @JsonProperty(value = "result_left")
    private String resultLeft;

    @JsonProperty(value = "result_right")
    private String resultRight;

    @JsonProperty(value = "result_numeric")
    private String resultNumeric;
}

