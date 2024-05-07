package com.base64.gamesback.speciality.controller;

import com.base64.gamesback.speciality.dto.SpecialityDto;
import com.base64.gamesback.speciality.dto.SpecialityResponse;
import com.base64.gamesback.speciality.entity.Speciality;
import com.base64.gamesback.speciality.service.SpecialityService;
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
@RequestMapping("/security/speciality")
public class SpecialityController {

    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }


    @PostMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Create new speciality"
    )
    public ResponseEntity<HttpStatus> registerSpeciality(@Valid @RequestBody SpecialityDto specialityDto){
        specialityService.registerSpeciality(specialityDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get speciality by id"
    )
    public ResponseEntity<Speciality> getSpecialityById(@Parameter(description = "UUID of a speciality", required = true) @PathVariable("id") UUID specialityId){
        return new ResponseEntity<>(specialityService.getSpecialityById(specialityId), HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get all specialities"
    )
    public ResponseEntity<List<SpecialityResponse>> getAllSpecialities(){
        return new ResponseEntity<>(specialityService.getAllSpecialities(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Update speciality by id"
    )
    public ResponseEntity<HttpStatus> updateSpecialityById(
            @Valid @RequestBody SpecialityDto specialityDto,
            @Parameter(description = "UUID of a speciality", required = true) @PathVariable("id") UUID specialityId
    ){
        specialityService.updateSpeciality(specialityDto, specialityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Delete speciality by id"
    )
    public ResponseEntity<HttpStatus> deleteSpecialityById(
            @Parameter(description = "UUID of a speciality", required = true) @PathVariable("id") UUID specialityId
    ){
        specialityService.deleteSpeciality(specialityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/doctor/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get speciality by doctor id"
    )
    public ResponseEntity<List<SpecialityResponse>> getSpecialityByDoctorId(@Parameter(description = "UUID of a doctor", required = true) @PathVariable("id") UUID doctorId){
        return new ResponseEntity<>(specialityService.getAllSpecialitiesByDoctorId(doctorId), HttpStatus.OK);
    }
}
