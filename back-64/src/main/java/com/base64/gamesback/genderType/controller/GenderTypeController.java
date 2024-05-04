package com.base64.gamesback.genderType.controller;

import com.base64.gamesback.genderType.dto.GenderTypeResponse;
import com.base64.gamesback.genderType.service.GenderTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/security/gender_type")
public class GenderTypeController {

    private final GenderTypeService genderTypeService;

    public GenderTypeController(GenderTypeService genderTypeService) {
        this.genderTypeService = genderTypeService;
    }

    @GetMapping("/")
    @Operation(description = "Get List of all gender types")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<GenderTypeResponse>> getAllGenderTypes(){
        return new ResponseEntity<>(genderTypeService.getAllGenderTypes(), HttpStatus.OK);
    }
}
