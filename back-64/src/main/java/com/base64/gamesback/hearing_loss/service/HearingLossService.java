package com.base64.gamesback.hearing_loss.service;

import com.base64.gamesback.hearing_loss.dto.HearingLossDto;
import com.base64.gamesback.hearing_loss.dto.HearingLossResponse;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface HearingLossService {

    void registerHearingLoss(HearingLossDto hearingLossDto);
    void updateHearingLoss(HearingLossDto hearingLossDto, UUID hearingLossId);
    void deleteHearingLoss(UUID hearingLossId);
    HearingLoss getHearingLossById(UUID hearingLossId);
    List<HearingLossResponse> getAllHearingLoss();
    void assignHearingLosses(Set<UUID> hearingLossIds, UUID personId);
}
