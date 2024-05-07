package com.base64.gamesback.auth.user.dto;

import com.base64.gamesback.speciality.dto.SpecialityResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class UserDoctorResponse {

    @JsonProperty(value = "user_id")
    private UUID userId;

    @NotNull
    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "administrator")
    private Boolean administrator;

    @JsonProperty(value = "doctor_name")
    private String name;

    @JsonProperty(value = "doctor_last_name")
    private String lastname;

    @JsonProperty(value = "doctor_phone")
    private String phone;

    @JsonProperty(value = "doctor_address")
    private String address;

    @JsonProperty(value = "doctor_email")
    private String email;

    @JsonProperty(value = "doctor_image")
    private String image;

    @JsonProperty(value = "doctor_description")
    private String description;

    @JsonProperty(value = "specialities")
    private List<SpecialityResponseDto> specialities;

    public UserDoctorResponse(UUID userId, String userName, Boolean administrator, String name, String lastname, String phone, String address, String email, String image, String description) {
        this.userId = userId;
        this.userName = userName;
        this.administrator = administrator;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
        this.description = description;
    }

    public void addResponseSpeciality(List<SpecialityResponseDto> specialities) {
        this.specialities = specialities;
    }
}
