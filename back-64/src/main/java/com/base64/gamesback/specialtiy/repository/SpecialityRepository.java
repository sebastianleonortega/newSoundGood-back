package com.base64.gamesback.specialtiy.repository;

import com.base64.gamesback.specialtiy.dto.SpecialityResponse;
import com.base64.gamesback.specialtiy.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Specialty, UUID> {

    List<Specialty> getSpecialtyByNameContainingIgnoreCase(String name);

    @Query("SELECT C.specialtyId as specialityId, C.name as specialityName FROM Specialty C")
    List<SpecialityResponse> getAllSpecialities();

    Set<Specialty> getSpecialtyBySpecialtyIdIn(List<UUID> specialityId);

    @Query(value = "SELECT s.specialty_id AS specialityId, s.name AS specialityName " +
            "FROM main.specialty s " +
            "INNER JOIN main.doctor_speciality ds ON s.specialty_id = ds.speciality_id " +
            "INNER JOIN main.doctor d ON ds.doctor_id = d.user_id " +
            "WHERE d.user_id = :doctorId", nativeQuery = true)
    List<SpecialityResponse> getAllSpecialitiesByDoctorId(@Param("doctorId") UUID doctorId);

}
