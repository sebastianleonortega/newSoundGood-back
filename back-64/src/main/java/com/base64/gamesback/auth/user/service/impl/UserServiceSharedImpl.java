package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.auth.exception.AuthenticationFailedException;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.UserRepository;
import com.base64.gamesback.auth.user.service.UserServiceShared;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class UserServiceSharedImpl implements UserServiceShared {

    private final UserRepository userRepository;

    public UserServiceSharedImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("El usuario no se encuentra"));
    }

    @Override
    public User getUserUserName(String userName) {
        return userRepository.getUserByUserName(userName.toLowerCase(Locale.ROOT));
    }

    @Override
    public User getUserNameAndEmail(String userName, String email) {
        return userRepository.getUserByUserNameAndPersonPersonEmail(userName,email);
    }

    @Override
    public User getUserOfResetPassword(UUID userId, String userName) {
        return userRepository.findByUserIdAndUserName(userId,userName).orElseThrow(()-> new AuthenticationFailedException("usuario no encontrado"));
    }
}
