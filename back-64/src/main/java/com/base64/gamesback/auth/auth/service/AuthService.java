package com.base64.gamesback.auth.auth.service;

import com.base64.gamesback.auth.auth.dto.AuthenticationMfaRequest;
import com.base64.gamesback.auth.auth.dto.LoginRequest;
import com.base64.gamesback.auth.auth.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse mfa(AuthenticationMfaRequest request);

}
