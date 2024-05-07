package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.user.dto.DoctorDto;
import com.base64.gamesback.auth.user.dto.DoctorUpdateRequest;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.DoctorRepository;
import com.base64.gamesback.auth.user.service.DoctorService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.speciality.service.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialityService specialityService;


    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecialityService specialityService) {
        this.doctorRepository = doctorRepository;
        this.specialityService = specialityService;
    }

    @Override
    public void registerPersonDoctor(User user, DoctorDto doctorDto) {
        Doctor doctor = Doctor.create(
                doctorDto.getName(),
                doctorDto.getLastname(),
                doctorDto.getPhone(),
                doctorDto.getAddress(),
                doctorDto.getEmail(),
                doctorDto.getImage() != null ? doctorDto.getImage() : "",
                doctorDto.getDescription()
        );
        doctor.addUser(user);
        doctorRepository.save(doctor);
        specialityService.assignSpecialities(Arrays.asList(doctorDto.getSpecialities()), doctor.getDoctorId());

    }

    @Override
    public void updatePersonDoctor(DoctorUpdateRequest doctorUpdateRequest, User user) {
        Doctor doctor = this.getDoctorById(user.getUserId());
        doctor.updateDoctor(
                doctorUpdateRequest.getName(),
                doctorUpdateRequest.getLastname(),
                doctorUpdateRequest.getPhone(),
                doctorUpdateRequest.getAddress(),
                doctorUpdateRequest.getEmail(),
                doctorUpdateRequest.getImage(),
                doctorUpdateRequest.getDescription()
        );
        doctorRepository.save(doctor);
        specialityService.assignSpecialities(Arrays.asList(doctorUpdateRequest.getSpecialities()), doctor.getDoctorId());
    }

    @Override
    public Doctor getDoctorById(UUID doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("No existe el doctor buscado"));
    }
}
