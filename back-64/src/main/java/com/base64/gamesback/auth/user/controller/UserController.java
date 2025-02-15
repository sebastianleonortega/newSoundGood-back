package com.base64.gamesback.auth.user.controller;

import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.dto.projection.CountUsersAndTest;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.auth.user.service.UserService;
import com.base64.gamesback.common.object.SearchByCriteria;
import com.base64.gamesback.common.parse.ParseFilters;
import com.base64.gamesback.email.service.EmailSendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PersonService personService;
    private final EmailSendService emailSendService;

    public UserController(UserService userService, PersonService personService, EmailSendService emailSendService) {
        this.userService = userService;
        this.personService = personService;
        this.emailSendService = emailSendService;
    }

    @GetMapping("/patient/")
    public ResponseEntity<List<UserPatientResponse>> getAllUsersPatient() {
        return new ResponseEntity<>(userService.getAllUsersPatient(), HttpStatus.OK);
    }

    @GetMapping("/person_page")
    public ResponseEntity<Page<UserPatientResponse>> getAllUsersPatientPageByDoctorId(@RequestParam Map<String, Serializable> params) {
        SearchByCriteria search = new SearchByCriteria(
                ParseFilters.parseFilters(params),
                Optional.ofNullable((String) params.get("order_by")),
                Optional.ofNullable((String) params.get("order")),
                ParseFilters.serializableToOptionalInteger(params.get("limit")),
                ParseFilters.serializableToOptionalInteger(params.get("offset"))
        );
        return new ResponseEntity<>(userService.getAllUsersPatientPage(search), HttpStatus.OK);
    }

    @GetMapping("/patient/{userId}")
    public ResponseEntity<UserPatientResponse> getPatientById(@Valid @PathVariable UUID userId) {
        return new ResponseEntity<>(userService.getUserPatientById(userId), HttpStatus.OK);
    }

    @GetMapping("/doctor/")
    public ResponseEntity<List<UserDoctorResponse>> getAllUsersDoctor() {
        return new ResponseEntity<>(userService.getAllUsersDoctor(), HttpStatus.OK);
    }

    @GetMapping("/doctor_page")
    public ResponseEntity<Page<UserDoctorResponse>> getAllUsersDoctorPage(@RequestParam Map<String, Serializable> params) {
        SearchByCriteria search = new SearchByCriteria(
                ParseFilters.parseFilters(params),
                Optional.ofNullable((String) params.get("order_by")),
                Optional.ofNullable((String) params.get("order")),
                ParseFilters.serializableToOptionalInteger(params.get("limit")),
                ParseFilters.serializableToOptionalInteger(params.get("offset"))
        );
        return new ResponseEntity<>(userService.getAllUsersDoctorPage(search), HttpStatus.OK);
    }

    @GetMapping("/user_page/")
    public ResponseEntity<Page<UserResponseDto>> getAllUsersPage(@RequestParam Map<String, Serializable> params) {
        SearchByCriteria search = new SearchByCriteria(
                ParseFilters.parseFilters(params),
                Optional.ofNullable((String) params.get("order_by")),
                Optional.ofNullable((String) params.get("order")),
                ParseFilters.serializableToOptionalInteger(params.get("limit")),
                ParseFilters.serializableToOptionalInteger(params.get("offset"))
        );
        return new ResponseEntity<>(userService.getAllUsersPage(search), HttpStatus.OK);
    }

    @GetMapping("/doctor/{userId}")
    public ResponseEntity<UserDoctorResponse> getDoctorById(@Valid @PathVariable UUID userId) {
        return new ResponseEntity<>(userService.getUserDoctorById(userId), HttpStatus.OK);
    }

    @PostMapping("/patient/")
    @Operation(description = "Create a new user patient")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<UUID> createUserPatient(@Valid @RequestBody UserDto request) {
        UUID userId = userService.registerUserPatient(request);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @PostMapping("/doctor/")
    @Operation(description = "Create a new user doctor")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<UUID> createUserDoctor(@Valid @RequestBody UserDoctorDto request) {
        UUID userId = userService.registerUserDoctor(request);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @PostMapping("/send_user_credentials/{userId}")
    @Operation(description = "Send user credentials")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<HttpStatus> sendCodeAuth(@Valid @PathVariable UUID userId) {
        emailSendService.sendUserCredentials(userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/exist_username/{userName}")
    @Operation(description = "exist by userName")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<Boolean> responseName(@Valid @PathVariable String userName) {
        return new ResponseEntity<>(userService.existUserByName(userName), HttpStatus.OK);
    }

    @GetMapping("/exist_email/{personEmail}")
    @Operation(description = "exist person by email")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<Boolean> responseEmail(@Valid @PathVariable String personEmail) {
        return new ResponseEntity<>(personService.existPersonByEmail(personEmail), HttpStatus.OK);
    }

    @GetMapping("/exist_document/{personDocument}")
    @Operation(description = "exist person by document")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<Boolean> personDocument(@Valid @PathVariable String personDocument) {
        return new ResponseEntity<>(personService.existPersonByDocument(personDocument), HttpStatus.OK);
    }

    @PutMapping("/patient/{id}")
    @Operation(description = "update user patient")
    @ApiResponse(responseCode = "204", description = "no content")
    public ResponseEntity<HttpStatus> updatePersonPatient(@Valid @RequestBody UserUpdateRequest request, @Valid @PathVariable("id") UUID id) {
        userService.updateUserPatient(request, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/doctor/{id}")
    @Operation(description = "update user doctor")
    @ApiResponse(responseCode = "204", description = "no content")
    public ResponseEntity<HttpStatus> updatePersonDoctor(@Valid @RequestBody UserDoctorUpdateRequest request, @Valid @PathVariable("id") UUID id) {
        userService.updateUserDoctor(request, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update_password")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")}, description = "UPDATE PASSWORD")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "NOT CONTENT"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<HttpStatus> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/v1/reset_password_by_admin/{user_id}/{user_admin_id}")
    @Operation(description = "Reset password of user by administrator")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<HttpStatus> resetPasswordUser(
            @Parameter(description = "UUID of a user", required = true) @PathVariable("user_id") UUID userId,
            @Parameter(description = "UUID of a user admin", required = true) @PathVariable("user_admin_id") UUID userAdminId) {
        userService.updatePasswordByAdmin(userId, userAdminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/forgot_password")
    @Operation(description = "Forgot password by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    public ResponseEntity<HttpStatus> forgotPassword(@Valid @RequestBody ResetPasswordRequest request) {
        userService.forgotPassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset_password")
    @Operation(description = "Reset password by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    public ResponseEntity<HttpStatus> resetPassword(@Valid @RequestBody TokenResentPasswordRequest request) {
        userService.verifyTokenResetPassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update_status_user")
    @Operation(description = "update status user")
    @ApiResponse(responseCode = "204", description = "no content")
    public ResponseEntity<HttpStatus> updateStatusUser(@Valid @RequestBody UpdateStatusUserRequest updateStatusUserRequest) {
        userService.updateStatusUser(updateStatusUserRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/count_users_and_test")
    @Operation(description = "count user and test on platform")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<CountUsersAndTest> countUsersAndTest() {
        return new ResponseEntity<>(userService.getCountUsersAndTest(), HttpStatus.OK);
    }
}
