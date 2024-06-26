package com.base64.gamesback.speciality.service.impl;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.repository.DoctorRepository;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.speciality.dto.SpecialityDto;
import com.base64.gamesback.speciality.dto.SpecialityResponse;
import com.base64.gamesback.speciality.entity.Speciality;
import com.base64.gamesback.speciality.repository.SpecialityRepository;
import com.base64.gamesback.speciality.service.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specialityRepository;
    private final DoctorRepository doctorRepository;


    public SpecialityServiceImpl(SpecialityRepository specialityRepository, DoctorRepository doctorRepository) {
        this.specialityRepository = specialityRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void registerSpeciality(SpecialityDto specialityDto) {

        List<Speciality> specialties = specialityRepository.getSpecialityByNameContainingIgnoreCase(specialityDto.getSpecialityName());
        if (!specialties.isEmpty()) {
            throw new IllegalArgumentException("Ya existe una especialidad con ese nombre");
        }

        Speciality speciality = com.base64.gamesback.speciality.entity.Speciality.create(
                specialityDto.getSpecialityName()
        );
        specialityRepository.save(speciality);
    }


    @Override
    public void updateSpeciality(SpecialityDto specialityDto, UUID specialityId) {
       Speciality speciality = this.getSpecialityById(specialityId);
        speciality.updateSpeciality(specialityDto.getSpecialityName());
        specialityRepository.save(speciality);
    }

    @Override
    public void deleteSpeciality(UUID specialityId) {
        Speciality speciality = this.getSpecialityById(specialityId);
        specialityRepository.delete(speciality);
    }

    @Override
    public Speciality getSpecialityById(UUID specialityId) {
        return specialityRepository.findById(specialityId).orElseThrow(() -> new ResourceNotFoundException("No existe la especialidad a buscar"));
    }

    @Override
    public List<SpecialityResponse> getAllSpecialities() {
        return specialityRepository.getAllSpecialities();
    }

    @Override
    public void assignSpecialities(List<UUID> specialityIds, UUID doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("No existe el doctor buscado"));
        Set<Speciality> specialties = specialityRepository.getSpecialityBySpecialityIdIn(specialityIds);

        if(specialties.isEmpty()){
            throw new IllegalArgumentException("Las especialidades ingresadas no existen");
        }

        doctor.addSpecialities(specialties);
        doctorRepository.save(doctor);
    }
}
