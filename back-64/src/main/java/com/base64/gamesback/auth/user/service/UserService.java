package com.base64.gamesback.auth.user.service;


import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.dto.projection.userData;
import com.base64.gamesback.auth.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    userData getUserById(UUID userId);

    Boolean existUserByName(String userName);

    void registerUserPatient(UserDto request);

    void registerUserDoctor(UserDoctorDto request);

    void updateUser(UserUpdateRequest request, UUID userId);

    List<userData> getAllUsers();
}
