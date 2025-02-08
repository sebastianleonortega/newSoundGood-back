package com.base64.gamesback.auth.user.service;

import com.base64.gamesback.auth.user.entity.User;

import java.util.UUID;

public interface UserServiceShared {

    void saveUser(User user);

    User getUserById(UUID userId);

    User getUserUserName(String userName);

    User getUserNameAndEmail(String userName, String email);

    User getUserOfResetPassword(UUID userId,String userName);
}
