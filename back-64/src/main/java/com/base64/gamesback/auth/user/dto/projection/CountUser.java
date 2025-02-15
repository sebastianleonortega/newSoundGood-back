package com.base64.gamesback.auth.user.dto.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CountUser {

    @JsonProperty(value = "user_doctor")
    Integer getUserDoctor();

    @JsonProperty(value = "user_person")
    Integer getUserPerson();
}
