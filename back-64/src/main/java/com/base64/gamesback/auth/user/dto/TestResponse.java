package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class TestResponse {

    @JsonProperty(value = "person_name")
    private String personName;

    @JsonProperty(value = "test_id")
    private UUID testId;

    @JsonProperty(value = "test_type")
    private String testType;

    @JsonProperty(value = "result")
    private String result;

    @JsonProperty(value = "test_date")
    private LocalDateTime testDate;

    public TestResponse(String personName, String personLastName, UUID testId, String testType, String result, LocalDateTime testDate) {
        this.personName = personName + " " + personLastName;
        this.testId = testId;
        this.testType = testType;
        this.result = result;
        this.testDate = testDate;
    }
}

