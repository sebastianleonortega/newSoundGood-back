package com.base64.gamesback.auth.user.controller;

import com.base64.gamesback.auth.user.dto.UserDoctorDto;
import com.base64.gamesback.auth.user.dto.UserDto;
import com.base64.gamesback.auth.user.dto.UserUpdateRequest;
import com.base64.gamesback.auth.user.dto.projection.userData;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.auth.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PersonService personService;

    public UserController(UserService userService, PersonService personService) {
        this.userService = userService;
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<List<userData>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<userData> getById(@Valid @PathVariable UUID userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/patient/")
    @Operation(description = "Create a new user patient" )
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<HttpStatus> createUserPatient(@Valid @RequestBody UserDto request){
         userService.registerUserPatient(request);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/doctor/")
    @Operation(description = "Create a new user doctor" )
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<HttpStatus> createUserDoctor(@Valid @RequestBody UserDoctorDto request){
        userService.registerUserDoctor(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/exist_username/{userName}")
    @Operation(description = "exist by userName" )
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<Boolean> responseName(@Valid @PathVariable String userName){
        return new ResponseEntity<>(userService.existUserByName(userName), HttpStatus.OK);
    }

    @GetMapping("/exist_email/{personEmail}")
    @Operation(description = "exist person by email" )
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<Boolean> responseEmail(@Valid @PathVariable String personEmail){
        return new ResponseEntity<>(personService.existPersonByEmail(personEmail), HttpStatus.OK);
    }

    @GetMapping("/exist_document/{personDocument}")
    @Operation(description = "exist person by document" )
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<Boolean> personDocument(@Valid @PathVariable String personDocument){
        return new ResponseEntity<>(personService.existPersonByDocument(personDocument), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    @Operation(description = "update user")
    @ApiResponse(responseCode = "204", description = "no content")
    public ResponseEntity<HttpStatus> updatePerson(@Valid @RequestBody UserUpdateRequest request, @Valid @PathVariable UUID uuid){
        userService.updateUser(request, uuid);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
