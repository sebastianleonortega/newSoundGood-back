package com.base64.gamesback.auth.user.service;

import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.dto.projection.userDoctorData;
import com.base64.gamesback.auth.user.dto.projection.userPersonData;
import com.base64.gamesback.auth.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserPatientResponse getUserPatientById(UUID userId);

    Boolean existUserByName(String userName);

    void registerUserPatient(UserDto request);

    void registerUserDoctor(UserDoctorDto request);

    void updateUserPatient(UserUpdateRequest request, UUID userId);

    void updateUserDoctor(UserDoctorUpdateRequest request, UUID userId);

    List<UserPatientResponse> getAllUsersPatient();

    UserDoctorResponse getUserDoctorById(UUID userId);

    List<UserDoctorResponse> getAllUsersDoctor();

    void updatePassword(UpdatePasswordRequest request);

    void updatePasswordByAdmin(UUID userId, UUID userAdminId);

    void forgotPassword(ResetPasswordRequest request);

    void verifyTokenResetPassword(TokenResentPasswordRequest request);
}
