package com.base64.gamesback.auth.auth.service.impl;

import com.base64.gamesback.auth.auth.dto.LoginRequest;
import com.base64.gamesback.auth.auth.dto.LoginResponse;
import com.base64.gamesback.auth.auth.service.AuthService;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.UserRepository;
import com.base64.gamesback.common.exception_handler.AccessDeniedException;
import com.base64.gamesback.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    public LoginResponse login(LoginRequest request) {
       User user = userRepository.getUserByUserName(request.getName().toLowerCase(Locale.ROOT));

       if(user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())){
           String token = jwtUtil.create(String.valueOf(user.getUserId()), user.getPerson().getPersonEmail());
           return LoginResponse.create(user.getUserId(), user.getProfileImage(), user.isAdministrator(), user.getPerson().getPersonName(), user.getPerson().getPersonLastName(), user.getDoctor() != null, token);
       }else {
           throw new AccessDeniedException("Credenciales incorrectas");
       }
    }

}
