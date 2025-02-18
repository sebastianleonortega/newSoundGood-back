package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "result_left")
    private String resultLeft;

    @JsonProperty(value = "result_right")
    private String resultRight;

    @JsonProperty(value = "result_numeric")
    private String resultNumeric;

    @JsonProperty(value = "test_date")
    private LocalDateTime testDate;

    public TestResponse(String personName, String personLastName, UUID testId, String testType, String resultLeft, String resultRight, String resultNumeric, LocalDateTime testDate) {
        this.personName = personName + " " + personLastName;
        this.testId = testId;
        this.testType = testType;
        this.resultLeft = resultLeft;
        this.resultRight = resultRight;
        this.resultNumeric = resultNumeric;
        this.testDate = testDate;
    }
}

