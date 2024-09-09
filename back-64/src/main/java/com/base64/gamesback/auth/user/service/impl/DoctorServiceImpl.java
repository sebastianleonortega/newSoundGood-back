package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.user.dto.DoctorDto;
import com.base64.gamesback.auth.user.dto.DoctorUpdateRequest;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.DoctorRepository;
import com.base64.gamesback.auth.user.service.DoctorService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.documentType.service.DocumentTypeSharedService;
import com.base64.gamesback.genderType.service.GenderTypeSharedService;
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
    private final DocumentTypeSharedService documentTypeSharedService;
    private final GenderTypeSharedService genderTypeSharedService;


    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecialityService specialityService, DocumentTypeSharedService documentTypeSharedService, GenderTypeSharedService genderTypeSharedService) {
        this.doctorRepository = doctorRepository;
        this.specialityService = specialityService;
        this.documentTypeSharedService = documentTypeSharedService;
        this.genderTypeSharedService = genderTypeSharedService;
    }

    @Override
    public void registerPersonDoctor(User user, DoctorDto doctorDto) {
        Doctor doctor = Doctor.create(
                doctorDto.getName(),
                doctorDto.getLastname(),
                doctorDto.getDocument(),
                doctorDto.getPhone(),
                doctorDto.getAddress(),
                doctorDto.getEmail(),
                doctorDto.getImage() != null ? doctorDto.getImage() : "",
                doctorDto.getDescription()
        );
        doctor.addUser(user);
        doctor.addDocumentType(documentTypeSharedService.getDocumentType(doctorDto.getDocumentType()));
        doctor.addGenderType(genderTypeSharedService.getGenderTypeById(doctorDto.getGenderType()));
        doctorRepository.save(doctor);
        specialityService.assignSpecialities(Arrays.asList(doctorDto.getSpecialities()), doctor.getDoctorId());

    }

    @Override
    public void updatePersonDoctor(DoctorUpdateRequest doctorUpdateRequest, User user) {
        Doctor doctor = this.getDoctorById(user.getUserId());
        doctor.updateDoctor(
                doctorUpdateRequest.getName(),
                doctorUpdateRequest.getLastname(),
                doctorUpdateRequest.getDocument(),
                doctorUpdateRequest.getPhone(),
                doctorUpdateRequest.getAddress(),
                doctorUpdateRequest.getEmail(),
                doctorUpdateRequest.getImage(),
                doctorUpdateRequest.getDescription()
        );
        doctor.addDocumentType(documentTypeSharedService.getDocumentType(doctorUpdateRequest.getDocumentType()));
        doctor.addGenderType(genderTypeSharedService.getGenderTypeById(doctorUpdateRequest.getGenderType()));
        doctorRepository.save(doctor);
        specialityService.assignSpecialities(Arrays.asList(doctorUpdateRequest.getSpecialities()), doctor.getDoctorId());
    }

    @Override
    public Doctor getDoctorById(UUID doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("No existe el doctor buscado"));
    }
}
