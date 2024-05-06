package com.base64.gamesback.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DoctorDto {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    @JsonProperty(value = "doctor_name")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    @JsonProperty(value = "doctor_last_name")
    private String lastname;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 15)
    @JsonProperty(value = "doctor_phone")
    private String phone;

    @NotNull
    @Size(max = 50)
    @JsonProperty(value = "doctor_address")
    private String address;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    @Email
    @JsonProperty(value = "doctor_email")
    private String email;

    @JsonProperty(value = "doctor_image")
    private String image;

    @JsonProperty(value = "specialities", required = true)
    @Size(min = 1)
    private UUID[] specialities;

}
