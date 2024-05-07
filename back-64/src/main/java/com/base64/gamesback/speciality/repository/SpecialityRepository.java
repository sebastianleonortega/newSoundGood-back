package com.base64.gamesback.speciality.repository;

import com.base64.gamesback.speciality.dto.SpecialityResponse;
import com.base64.gamesback.speciality.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {

    List<Speciality> getSpecialityByNameContainingIgnoreCase(String name);

    @Query("SELECT C.specialityId as specialityId, C.name as specialityName FROM Speciality C")
    List<SpecialityResponse> getAllSpecialities();

    Set<Speciality> getSpecialityBySpecialityIdIn(List<UUID> specialityId);

    @Query(value = "SELECT s.speciality_id AS specialityId, s.name AS specialityName " +
            "FROM main.speciality s " +
            "INNER JOIN main.doctor_speciality ds ON s.speciality_id = ds.speciality_id " +
            "INNER JOIN main.doctor d ON ds.doctor_id = d.user_id " +
            "WHERE d.user_id = :doctorId", nativeQuery = true)
    List<SpecialityResponse> getAllSpecialitiesByDoctorId(@Param("doctorId") UUID doctorId);

}
