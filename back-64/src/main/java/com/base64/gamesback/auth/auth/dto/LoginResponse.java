package com.base64.gamesback.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    @JsonProperty(value = "user_id")
    private UUID userId;

    @JsonProperty(value = "user_profile")
    private String useProfile;

    @JsonProperty(value = "administrator")
    private Boolean administrator;

    @JsonProperty(value = "person_name")
    private String personName;

    @JsonProperty(value = "person_last_name")
    private String personLastName;

    @JsonProperty(value = "is_doctor")
    private Boolean isDoctor;

    @JsonProperty(value = "token")
    private String token;

    public LoginResponse(UUID userId) {
        this.userId = userId;
    }

    public LoginResponse(String useProfile, Boolean administrator, String personName, String personLastName, Boolean isDoctor, String token) {
        this.useProfile = useProfile;
        this.administrator = administrator;
        this.personName = personName;
        this.personLastName = personLastName;
        this.isDoctor = isDoctor;
        this.token = token;
    }

    public static LoginResponse create(String useProfile, Boolean administrator, String personName, String personLastName, Boolean isDoctor, String token) {
        return new LoginResponse(useProfile, administrator, personName, personLastName, isDoctor, token);
    }


}
