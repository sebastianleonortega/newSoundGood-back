package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.UserDoctorResponse;
import com.base64.gamesback.auth.user.dto.UserPatientResponse;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryCustom {

    UserDoctorResponse getUserDoctorById(UUID userId);
    UserPatientResponse getUserPatientById(UUID userId);
    List<UserDoctorResponse> getAllUserDoctors();
    List<UserPatientResponse> getAllUserPatients();
}
