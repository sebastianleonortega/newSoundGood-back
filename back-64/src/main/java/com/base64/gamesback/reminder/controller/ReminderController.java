package com.base64.gamesback.reminder.controller;

import com.base64.gamesback.reminder.dto.ReminderDto;
import com.base64.gamesback.reminder.dto.ReminderResponse;
import com.base64.gamesback.reminder.dto.ReminderUpdateDto;
import com.base64.gamesback.reminder.service.ReminderService;
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
@RequestMapping("/security/reminder")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }


    @PostMapping("/")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Create new reminder"
    )
    public ResponseEntity<HttpStatus> registerReminder(@Valid @RequestBody ReminderDto reminderDto){
        reminderService.registerReminder(reminderDto);
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
            description = "Get reminder by id"
    )
    public ResponseEntity<ReminderResponse> getReminderById(@Parameter(description = "UUID of a reminder", required = true) @PathVariable("id") UUID reminderId){
        return new ResponseEntity<>(reminderService.getReminderResponseById(reminderId), HttpStatus.OK);
    }

    @GetMapping("/person/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Get reminders by person id"
    )
    public ResponseEntity<List<ReminderResponse>> getRemindersByPersonId(@Parameter(description = "UUID of a person", required = true) @PathVariable("id") UUID personId){
        return new ResponseEntity<>(reminderService.getAllReminderByPersonId(personId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Update reminder by id"
    )
    public ResponseEntity<HttpStatus> updateReminderById(
            @Valid @RequestBody ReminderUpdateDto reminderUpdateDto,
            @Parameter(description = "UUID of a reminder", required = true) @PathVariable("id") UUID reminderId
    ){
        reminderService.updateReminder(reminderUpdateDto, reminderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Not content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(
            security = {@SecurityRequirement(name = "bearer-key")},
            description = "Delete reminder by id"
    )
    public ResponseEntity<HttpStatus> deleteReminderById(
            @Parameter(description = "UUID of a reminder", required = true) @PathVariable("id") UUID reminderId
    ){
        reminderService.deleteReminder(reminderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
