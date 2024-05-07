package com.base64.gamesback.audition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AuditionResult {

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "valid_count")
    private int validCount;

    @JsonProperty(value = "invalid_numbers")
    private String invalidNumbers;

    public AuditionResult(String message, int validCount, String invalidNumbers) {
        this.message = message;
        this.validCount = validCount;
        this.invalidNumbers = invalidNumbers;
    }

}
