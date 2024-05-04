package com.base64.gamesback.hearing_loss.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class HearingLossResponseDto {

    @JsonProperty(value = "hearing_loss_id")
    private String hearingLossId;

    @JsonProperty(value = "hearing_loss_name")
    private String hearingLossName;
}
