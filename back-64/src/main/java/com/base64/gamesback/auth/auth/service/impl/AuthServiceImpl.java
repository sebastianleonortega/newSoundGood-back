package com.base64.gamesback.auth.auth.service.impl;

import com.base64.gamesback.auth.auth.dto.AuthenticationMfaRequest;
import com.base64.gamesback.auth.auth.dto.LoginRequest;
import com.base64.gamesback.auth.auth.dto.LoginResponse;
import com.base64.gamesback.auth.auth.exception.AuthenticationFailedException;
import com.base64.gamesback.auth.auth.service.AuthService;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.service.UserService;
import com.base64.gamesback.auth.user.util.GenericLoginAttempts;
import com.base64.gamesback.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
       User user = userService.getUserUserName(request.getName().toLowerCase(Locale.ROOT));

        if (user.isLocked()) {
            throw new AuthenticationFailedException("el usuario se encuentra bloqueado");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            if (user.getLoginAttempts() >= 2) {
                this.updateLockedUser(user);
            } else {
                this.updateLoginAttemptsUser(user, GenericLoginAttempts.LOGIN_ATTEMPTS);
            }
        }

        if(user.getDoctor() != null || user.isAdministrator()){
            user.updateLoginAttempts(0);
            user.addCodeVerification(this.generateCodeVerification(user));
            user.updateQuantityResentEmail(0);
            userService.saveUser(user);
        }

        if (user.getDoctor() == null && !user.isAdministrator()){
            String token = jwtUtil.create(String.valueOf(user.getUserId()), user.getPerson().getPersonEmail());
            return new LoginResponse(user.getProfileImage(), false, user.getPerson().getPersonName(), user.getPerson().getPersonLastName(), false, token);
        } else {
            return new LoginResponse(user.getUserId());
        }
    }

    @Override
    public LoginResponse mfa(AuthenticationMfaRequest request) {
        User user = userService.getUserById(request.getUserId());

        if (user.isLocked()) {
            throw new AuthenticationFailedException("el usuario se encuentra bloqueado");
        }

        if (user.getCreateCodeVerification() == null || !Objects.equals(user.getCodeVerification(), request.getCode())) {
            this.updateLoginAttemptsUser(user, GenericLoginAttempts.LOGIN_ATTEMPTS_MFA);
        }

        Duration duration = Duration.between(user.getCreateCodeVerification(), LocalDateTime.now());
        if (duration.toMinutes() > 5) {
            throw new AuthenticationFailedException("el código ha expirado, vuelve a iniciar sesión");
        }

        String token = jwtUtil.create(String.valueOf(user.getUserId()), (user.getPerson() != null) ? user.getPerson().getPersonEmail() : user.getDoctor().getEmail());

        user.updateLoginAttemptsMfa(0);
        user.resetCodeVerification();
        user.updateQuantityResentEmail(0);
        userService.saveUser(user);
        return new LoginResponse(user.getProfileImage(), user.isAdministrator(), (user.getPerson() != null) ? user.getPerson().getPersonName() : user.getDoctor().getName(), (user.getPerson() != null) ? user.getPerson().getPersonLastName() : user.getDoctor().getLastName(), user.getDoctor() != null, token);
    }

    private void updateLockedUser(User user) {
        user.updateLocked(true);
        userService.saveUser(user);
        throw new AuthenticationFailedException("usuario bloqueado debido a intentos fallidos de inicio de sesión.");
    }

    private void updateLoginAttemptsUser(User user, GenericLoginAttempts attempts) {
        if (GenericLoginAttempts.LOGIN_ATTEMPTS.equals(attempts)) {
            user.updateLoginAttempts(user.getLoginAttempts() + 1);
        } else {
            if (user.getLoginAttemptsMfa() >= 2) {
                this.updateLockedUser(user);
            }
            user.updateLoginAttemptsMfa(user.getLoginAttemptsMfa() + 1);
        }
        userService.saveUser(user);
        String message = (attempts.equals(GenericLoginAttempts.LOGIN_ATTEMPTS)) ? "." : " el código es inválido";
        throw new AuthenticationFailedException("quedan " + (attempts.equals(GenericLoginAttempts.LOGIN_ATTEMPTS) ? 3 - user.getLoginAttempts() : 3 - user.getLoginAttemptsMfa()) + " intentos" + message);
    }

    private String generateCodeVerification(User user) {
        if (user.getCodeVerification() != null && user.getCreateCodeVerification() != null && !user.getCodeVerification().isEmpty()) {
            Duration duration = Duration.between(user.getCreateCodeVerification(), LocalDateTime.now());
            if (duration.toMinutes() <= 30) {
                throw new AuthenticationFailedException("has alcanzado el límite de intentos para reenviar el código a tu correo, reintenta en " + (30 - duration.toMinutes()) + " minutos.");
            }
        }
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(999999);
        String codeVerification = String.format("%06d", code);
//        emailSendService.emailCodeVerification(user, codeVerification);
        return codeVerification;
    }

}
