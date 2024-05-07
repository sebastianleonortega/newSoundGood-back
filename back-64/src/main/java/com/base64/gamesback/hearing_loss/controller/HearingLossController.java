package com.base64.gamesback.hearing_loss.controller;

import com.base64.gamesback.hearing_loss.dto.HearingLossDto;
import com.base64.gamesback.hearing_loss.dto.HearingLossResponse;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;
import com.base64.gamesback.hearing_loss.service.HearingLossService;
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
@RequestMapping("/security/hearing_loss")
public class HearingLossController {

    private final HearingLossService hearingLossService;

    public HearingLossController(HearingLossService hearingLossService) {
        this.hearingLossService = hearingLossService;
    }


    @PostMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Create new hearing loss"
    )
    public ResponseEntity<HttpStatus> registerHearingLoss(@Valid @RequestBody HearingLossDto hearingLossDto){
        hearingLossService.registerHearingLoss(hearingLossDto);
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
            description = "Get hearing loss by id"
    )
    public ResponseEntity<HearingLoss> getHearingLossById(@Parameter(description = "UUID of a hearing loss", required = true) @PathVariable("id") UUID hearingLossId){
        return new ResponseEntity<>(hearingLossService.getHearingLossById(hearingLossId), HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get all hearing losses"
    )
    public ResponseEntity<List<HearingLossResponse>> getAllHearingLosses(){
        return new ResponseEntity<>(hearingLossService.getAllHearingLoss(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Update hearing loss by id"
    )
    public ResponseEntity<HttpStatus> updateHearingLossById(
            @Valid @RequestBody HearingLossDto hearingLossDto,
            @Parameter(description = "UUID of a hearing loss", required = true) @PathVariable("id") UUID hearingLossId
    ){
        hearingLossService.updateHearingLoss(hearingLossDto, hearingLossId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Delete hearing loss by id"
    )
    public ResponseEntity<HttpStatus> deleteSpecialityById(
            @Parameter(description = "UUID of a hearing loss", required = true) @PathVariable("id") UUID hearingLossId
    ){
        hearingLossService.deleteHearingLoss(hearingLossId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/person/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get hearing loss by person id"
    )
    public ResponseEntity<List<HearingLossResponse>> getHearingLossByPersonId(@Parameter(description = "UUID of a person", required = true) @PathVariable("id") UUID personId){
        return new ResponseEntity<>(hearingLossService.getAllHearingLossesByPersonId(personId), HttpStatus.OK);
    }
}
