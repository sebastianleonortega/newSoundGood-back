package com.base64.gamesback.hearing_loss.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class HearingLossResponseDto {

    @JsonProperty(value = "hearing_loss_id")
    private UUID hearingLossId;

    @JsonProperty(value = "hearing_loss_name")
    private String hearingLossName;

    public HearingLossResponseDto(UUID hearingLossId, String hearingLossName) {
        this.hearingLossId = hearingLossId;
        this.hearingLossName = hearingLossName;
    }
}
