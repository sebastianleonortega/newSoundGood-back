package com.base64.gamesback.auth.user.service;

import com.base64.gamesback.auth.user.dto.DoctorDto;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.User;

import java.util.UUID;

public interface DoctorService {

    void registerPersonDoctor(User user, DoctorDto doctorDto);
    Doctor getDoctorById(UUID doctorId);
}
