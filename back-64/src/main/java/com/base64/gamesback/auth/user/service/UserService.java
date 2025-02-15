package com.base64.gamesback.auth.user.service;

import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.dto.projection.userDoctorData;
import com.base64.gamesback.auth.user.dto.projection.userPersonData;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.common.object.SearchByCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserPatientResponse getUserPatientById(UUID userId);

    Boolean existUserByName(String userName);

    UUID registerUserPatient(UserDto request);

    UUID registerUserDoctor(UserDoctorDto request);

    void updateUserPatient(UserUpdateRequest request, UUID userId);

    void updateUserDoctor(UserDoctorUpdateRequest request, UUID userId);

    List<UserPatientResponse> getAllUsersPatient();

    UserDoctorResponse getUserDoctorById(UUID userId);

    List<UserDoctorResponse> getAllUsersDoctor();

    Page<UserDoctorResponse> getAllUsersDoctorPage(SearchByCriteria search);

    Page<UserResponseDto> getAllUsersPage(SearchByCriteria search);

    void updatePassword(UpdatePasswordRequest request);

    void updatePasswordByAdmin(UUID userId, UUID userAdminId);

    void forgotPassword(ResetPasswordRequest request);

    void verifyTokenResetPassword(TokenResentPasswordRequest request);

    void updateStatusUser(UpdateStatusUserRequest updateStatusUserRequest);
}
