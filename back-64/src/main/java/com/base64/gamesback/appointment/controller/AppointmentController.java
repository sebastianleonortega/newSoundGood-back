package com.base64.gamesback.appointment.controller;

import com.base64.gamesback.appointment.dto.AppointmentDto;
import com.base64.gamesback.appointment.entity.Appointment;
import com.base64.gamesback.appointment.service.AppointmentService;
import com.base64.gamesback.auth.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/")
    @Operation(description = "Create a new user appointment" )
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<HttpStatus> createUserPatient(@Valid @RequestBody AppointmentDto request){
        appointmentService.registerAppointment(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    @Operation(description = "Get all" )
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<List<Appointment>> createUserPatient(@Valid @PathVariable UUID uuid){
        return new ResponseEntity<>(appointmentService.getAppointmentByUser(uuid), HttpStatus.OK);

    }
}
