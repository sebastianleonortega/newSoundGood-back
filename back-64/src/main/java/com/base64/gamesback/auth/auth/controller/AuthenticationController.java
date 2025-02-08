package com.base64.gamesback.auth.auth.controller;


import com.base64.gamesback.auth.auth.dto.AuthenticationMfaRequest;
import com.base64.gamesback.auth.auth.dto.LoginRequest;
import com.base64.gamesback.auth.auth.dto.LoginResponse;
import com.base64.gamesback.auth.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AuthenticationController {

    private final AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/mfa")
    public ResponseEntity<LoginResponse> mfa(@Valid @RequestBody AuthenticationMfaRequest request){
        LoginResponse response = authService.mfa(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/resent_email/{id}")
    @Operation(description = "Resent email the code verification of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "500",description = "Server error")
    })
    public ResponseEntity<HttpStatus> resentEmailByUser(@Parameter(description = "UUID of a user",required = true) @PathVariable("id") UUID userId){
        authService.resentEmailByUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
