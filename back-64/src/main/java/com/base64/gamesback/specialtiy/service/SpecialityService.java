package com.base64.gamesback.specialtiy.service;

import com.base64.gamesback.specialtiy.dto.SpecialityDto;
import com.base64.gamesback.specialtiy.dto.SpecialityResponse;
import com.base64.gamesback.specialtiy.entity.Specialty;

import java.util.List;
import java.util.UUID;

public interface SpecialityService {

    void registerSpeciality(SpecialityDto specialityDto);
    void updateSpeciality(SpecialityDto specialityDto, UUID specialityId);
    void deleteSpeciality(UUID specialityId);
    Specialty getSpecialityById(UUID specialityId);
    List<SpecialityResponse> getAllSpecialities();
    void assignSpecialities(List<UUID> specialityIds, UUID doctorId);
}
