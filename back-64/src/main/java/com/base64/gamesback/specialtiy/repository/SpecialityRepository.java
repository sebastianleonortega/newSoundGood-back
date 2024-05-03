package com.base64.gamesback.specialtiy.repository;

import com.base64.gamesback.specialtiy.dto.SpecialityResponse;
import com.base64.gamesback.specialtiy.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Specialty, UUID> {

    List<Specialty> getSpecialtyByNameContainingIgnoreCase(String name);

    @Query("SELECT C.specialtyId as specialityId, C.name as specialityName FROM Specialty C")
    List<SpecialityResponse> getAllSpecialities();

    Set<Specialty> getSpecialtyBySpecialtyIdIn(List<UUID> specialityId);
}
