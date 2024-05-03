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

    @JsonProperty(value = "token")
    private String token;

    public LoginResponse(UUID userId, String useProfile, Boolean administrator, String personName, String personLastName, String token) {
        this.userId = userId;
        this.useProfile = useProfile;
        this.administrator = administrator;
        this.personName = personName;
        this.token = token;
        this.personLastName = personLastName;
    }

    public static LoginResponse create(UUID userId, String useProfile, Boolean administrator, String personName, String personLastName, String token) {
        return new LoginResponse(userId, useProfile, administrator, personName, personLastName, token);
    }


}
