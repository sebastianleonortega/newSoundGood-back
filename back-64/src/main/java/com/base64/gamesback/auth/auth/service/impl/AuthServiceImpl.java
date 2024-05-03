package com.base64.gamesback.auth.auth.service.impl;

import com.base64.gamesback.auth.auth.dto.LoginRequest;
import com.base64.gamesback.auth.auth.dto.LoginResponse;
import com.base64.gamesback.auth.auth.service.AuthService;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.UserRepository;
import com.base64.gamesback.common.exception_handler.AccessDeniedException;
import com.base64.gamesback.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }


    public LoginResponse login(LoginRequest request) {
       User user = userRepository.getUserByUserName(request.getName().toLowerCase(Locale.ROOT));

       if(user != null && Objects.equals(user.getPassword(), request.getPassword())){
           String token = jwtUtil.create(String.valueOf(user.getUserId()), user.getPerson().getPersonEmail());
           return LoginResponse.create(user.getUserId(), user.getProfileImage(), user.isAdministrator(), user.getPerson().getPersonName(), user.getPerson().getPersonLastName(), token);
       }else {
           throw new AccessDeniedException("Credenciales incrrectas");
       }
    }

}
