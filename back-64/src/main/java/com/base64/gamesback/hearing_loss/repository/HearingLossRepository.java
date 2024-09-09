package com.base64.gamesback.hearing_loss.repository;

import com.base64.gamesback.hearing_loss.dto.HearingLossResponse;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface HearingLossRepository extends JpaRepository<HearingLoss, UUID> {

    List<HearingLoss> getHearingLossByNameContainingIgnoreCase(String name);

    @Query("SELECT H.hearingLossId as hearingLossId, H.name as hearingLossName FROM HearingLoss H")
    List<HearingLossResponse> getAllHearingLosses();

    Set<HearingLoss> getHearingLossSpecialityByHearingLossIdIn(Set<UUID> specialityId);
}
