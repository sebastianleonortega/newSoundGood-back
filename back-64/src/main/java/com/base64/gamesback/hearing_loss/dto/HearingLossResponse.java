package com.base64.gamesback.hearing_loss.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public interface HearingLossResponse {

    @JsonProperty(value = "hearing_loss_id")
    UUID getHearingLossId();

    @JsonProperty(value = "hearing_loss_name")
    String getHearingLossName();
}
