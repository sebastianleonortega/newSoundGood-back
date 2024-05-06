package com.base64.gamesback.auth.user.service;


import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.dto.projection.userDoctorData;
import com.base64.gamesback.auth.user.dto.projection.userPersonData;

import java.util.List;
import java.util.UUID;

public interface UserService {

    userPersonData getUserPatientById(UUID userId);

    Boolean existUserByName(String userName);

    void registerUserPatient(UserDto request);

    void registerUserDoctor(UserDoctorDto request);

    void updateUserPatient(UserUpdateRequest request, UUID userId);

    void updateUserDoctor(UserDoctorUpdateRequest request, UUID userId);

    List<userPersonData> getAllUsersPatient();

    userDoctorData getUserDoctorById(UUID userId);

    List<userDoctorData> getAllUsersDoctor();
}
