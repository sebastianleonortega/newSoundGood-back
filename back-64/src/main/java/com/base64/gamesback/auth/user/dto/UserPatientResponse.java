package com.base64.gamesback.auth.user.dto;

import com.base64.gamesback.hearing_loss.dto.HearingLossResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
public class UserPatientResponse {

    @JsonProperty(value = "user_id")
    @JsonIgnore
    private UUID userId;

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "administrator")
    private boolean administrator;

    @JsonProperty(value = "profile_image")
    private String profileImage;

    @JsonProperty(value = "person_name")
    private String name;

    @JsonProperty(value = "person_last_name")
    private String lastname;

    @JsonProperty(value = "document_type", required = true)
    private UUID documentType;

    @JsonProperty(value = "document_type_code")
    private String documentTypeCode;

//    @NotNull
//    @JsonProperty(value = "gender_type", required = true)
//    private UUID genderType;

    @JsonProperty(value = "person_document")
    private String documentNumber;

    @Size(min = 8, max = 15)
    @JsonProperty(value = "person_phone")
    private String phone;

    @JsonProperty(value = "person_address")
    private String address;

    @JsonProperty(value = "person_email")
    private String email;

    @JsonProperty(value = "previous_treatments")
    private String previousTreatments;

    @JsonProperty(value = "hearing_losses")
    private List<HearingLossResponseDto> hearingLosses;

    public UserPatientResponse(UUID userId, String userName, Boolean administrator, String profileImage, String name, String lastname, UUID documentType, String documentTypeCode, String documentNumber, String phone, String address, String email, String previousTreatments) {
        this.userId = userId;
        this.userName = userName;
        this.administrator = administrator;
        this.profileImage = profileImage;
        this.name = name;
        this.lastname = lastname;
        this.documentType = documentType;
        this.documentTypeCode = documentTypeCode;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.previousTreatments = previousTreatments;
    }

    public void addResponseHearingLosses(List<HearingLossResponseDto> hearingLosses){
        this.hearingLosses = hearingLosses;
    }
}
