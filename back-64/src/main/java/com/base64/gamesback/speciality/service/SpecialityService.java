package com.base64.gamesback.speciality.service;

import com.base64.gamesback.speciality.dto.SpecialityDto;
import com.base64.gamesback.speciality.dto.SpecialityResponse;
import com.base64.gamesback.speciality.entity.Speciality;

import java.util.List;
import java.util.UUID;

public interface SpecialityService {

    void registerSpeciality(SpecialityDto specialityDto);
    void updateSpeciality(SpecialityDto specialityDto, UUID specialityId);
    void deleteSpeciality(UUID specialityId);
    Speciality getSpecialityById(UUID specialityId);
    List<SpecialityResponse> getAllSpecialities();
    void assignSpecialities(List<UUID> specialityIds, UUID doctorId);
    List<SpecialityResponse> getAllSpecialitiesByDoctorId(UUID doctorId);
}
