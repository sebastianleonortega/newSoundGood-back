package com.base64.gamesback.auth.user.controller;

import com.base64.gamesback.auth.user.dto.TestRequest;
import com.base64.gamesback.auth.user.dto.TestResponse;
import com.base64.gamesback.auth.user.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/test_patient")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/")
    @Operation(description = "Create a new test to patient")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<HttpStatus> createTestToPatient(@Valid @RequestBody TestRequest request) {
        testService.registerTestToPerson(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{person_id}")
    @Operation(description = "Get all test by person")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<List<TestResponse>> getAllTestByPerson(@Valid @PathVariable("person_id") UUID personId) {
        return new ResponseEntity<>(testService.getTestByPerson(personId), HttpStatus.OK);
    }

    @DeleteMapping("/{test_id}")
    @Operation(description = "Delete test by id")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<HttpStatus> deleteTestById(@Valid @PathVariable("test_id") UUID testId) {
        testService.deleteTestToPerson(testId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
