package com.base64.gamesback.medicines.controller;

import com.base64.gamesback.medicines.dto.MedicineDto;
import com.base64.gamesback.medicines.dto.MedicineResponse;
import com.base64.gamesback.medicines.entity.Medicine;
import com.base64.gamesback.medicines.service.MedicineService;
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
@RequestMapping("/security/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


    @PostMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Create new medicine"
    )
    public ResponseEntity<HttpStatus> registerMedicine(@Valid @RequestBody MedicineDto medicineDto){
        medicineService.registerMedicine(medicineDto);
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
            description = "Get medicine by id"
    )
    public ResponseEntity<Medicine> getMedicineById(@Parameter(description = "UUID of a medicine", required = true) @PathVariable("id") UUID medicineId){
        return new ResponseEntity<>(medicineService.getMedicineById(medicineId), HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get all medicines"
    )
    public ResponseEntity<List<MedicineResponse>> getAllMedicines(){
        return new ResponseEntity<>(medicineService.getAllMedicines(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Update medicine by id"
    )
    public ResponseEntity<HttpStatus> updateMedicineById(
            @Valid @RequestBody MedicineDto medicineDto,
            @Parameter(description = "UUID of a medicine", required = true) @PathVariable("id") UUID medicineId
    ){
        medicineService.updateMedicine(medicineDto, medicineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Delete medicine by id"
    )
    public ResponseEntity<HttpStatus> deleteMedicineById(
            @Parameter(description = "UUID of a medicine", required = true) @PathVariable("id") UUID medicineId
    ){
        medicineService.deleteMedicine(medicineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
