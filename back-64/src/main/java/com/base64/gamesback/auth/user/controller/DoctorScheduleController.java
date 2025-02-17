package com.base64.gamesback.auth.user.controller;

import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.service.DoctorScheduleService;
import com.base64.gamesback.auth.user.service.TestService;
import com.base64.gamesback.common.object.SearchByCriteria;
import com.base64.gamesback.common.parse.ParseFilters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

@RestController
@RequestMapping("/doctor_schedule")
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    public DoctorScheduleController(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @PostMapping("/{doctor_id}")
    @Operation(description = "Create doctor schedule")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<HttpStatus> createDoctorSchedule(@Valid @RequestBody Set<DoctorScheduleRequest> request, @Valid @PathVariable("doctor_id") UUID doctorId) {
        doctorScheduleService.registerDoctorSchedule(doctorId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{doctor_id}")
    @Operation(description = "Get doctor schedules")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<List<DoctorScheduleResponse>> getDoctorSchedulesByDoctor(@RequestParam Map<String, Serializable> params, @Valid @PathVariable("doctor_id") UUID doctorId) {
        SearchByCriteria search = new SearchByCriteria(
                ParseFilters.parseFilters(params),
                Optional.ofNullable((String) params.get("order_by")),
                Optional.ofNullable((String) params.get("order")),
                ParseFilters.serializableToOptionalInteger(params.get("limit")),
                ParseFilters.serializableToOptionalInteger(params.get("offset"))
        );
        return new ResponseEntity<>(doctorScheduleService.getDoctorScheduleByDoctorId(doctorId, search), HttpStatus.OK);
    }

    @PutMapping("/")
    @Operation(description = "Update status doctor schedule")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<HttpStatus> updateDoctorSchedule(@Valid @RequestBody DoctorScheduleUpdateRequest doctorScheduleUpdateRequest) {
        doctorScheduleService.updateDoctorSchedule(doctorScheduleUpdateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
