package com.base64.gamesback.speciality.repository;

import com.base64.gamesback.speciality.dto.SpecialityResponse;
import com.base64.gamesback.speciality.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {

    List<Speciality> getSpecialityByNameContainingIgnoreCase(String name);

    @Query("SELECT C.specialityId as specialityId, C.name as specialityName FROM Speciality C")
    List<SpecialityResponse> getAllSpecialities();

    Set<Speciality> getSpecialityBySpecialityIdIn(List<UUID> specialityId);

}
