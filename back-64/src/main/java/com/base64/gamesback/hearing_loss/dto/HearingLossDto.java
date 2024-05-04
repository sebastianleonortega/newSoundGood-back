package com.base64.gamesback.hearing_loss.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class HearingLossDto {

    @NotNull
    @JsonProperty(value = "hearing_loss_name")
    private String hearingLossName;
}
