package com.base64.gamesback.appointment.controller;

import com.base64.gamesback.appointment.dto.*;
import com.base64.gamesback.appointment.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/security/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Create new appointment"
    )
    public ResponseEntity<HttpStatus> createUserPatient(@Valid @RequestBody AppointmentRequest request){
        appointmentService.registerAppointment(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/person/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get all appointments by person id"
    )
    public ResponseEntity<List<AppointmentDataResponsePatient>> getAllAppointmentsByPersonId(@Parameter(description = "UUID of a person", required = true) @PathVariable("id") UUID personId){
        return new ResponseEntity<>(appointmentService.getAppointmentByPersonId(personId), HttpStatus.OK);

    }

    @GetMapping("/doctor/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get all appointments by doctor id"
    )
    public ResponseEntity<List<AppointmentDataResponseDoctor>> getAllAppointmentsByDoctorId(@Parameter(description = "UUID of a doctor", required = true) @PathVariable("id") UUID doctorId){
        return new ResponseEntity<>(appointmentService.getAppointmentByDoctorId(doctorId), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get appointment by id"
    )
    public ResponseEntity<AppointmentDataResponse> getAppointmentsById(@Parameter(description = "UUID of a appointment", required = true) @PathVariable("id") UUID appointmentId){
        return new ResponseEntity<>(appointmentService.getAppointmentByAppointmentId(appointmentId), HttpStatus.OK);

    }

    @PutMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Update appointment by id"
    )
    private ResponseEntity<HttpStatus> updateAppointment(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest){
        appointmentService.updateAppointment(appointmentUpdateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Delete appointment by id"
    )
    public ResponseEntity<HttpStatus> deleteSpecialityById(
            @Parameter(description = "UUID of a appointment", required = true) @PathVariable("id") UUID appointmentId
    ){
        appointmentService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
