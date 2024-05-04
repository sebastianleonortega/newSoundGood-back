package com.base64.gamesback.hearing_loss.repository;

import com.base64.gamesback.hearing_loss.dto.HearingLossResponse;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface HearingLossRepository extends JpaRepository<HearingLoss, UUID> {

    List<HearingLoss> getHearingLossByNameContainingIgnoreCase(String name);

    @Query("SELECT H.hearingLossId as hearingLossId, H.name as hearingLossName FROM HearingLoss H")
    List<HearingLossResponse> getAllHearingLosses();

    Set<HearingLoss> getHearingLossSpecialtyByHearingLossIdIn(List<UUID> specialityId);

    @Query(value = "SELECT h.hearing_loss_id AS hearingLossId, h.name AS hearingLossName " +
            "FROM main.hearing_loss h " +
            "INNER JOIN main.person_hearing_loss phl ON h.hearing_loss_id = phl.hearing_loss_id " +
            "INNER JOIN main.person p ON phl.person_id = p.user_id " +
            "WHERE p.user_id = :personId", nativeQuery = true)
    List<HearingLossResponse> getAllHearingLossesByPersonId(@Param("personId") UUID personId);

}
