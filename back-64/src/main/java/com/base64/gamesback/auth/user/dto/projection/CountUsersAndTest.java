package com.base64.gamesback.auth.user.dto.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CountUsersAndTest {

    @JsonProperty(value = "user_doctor")
    Integer getUserDoctor();

    @JsonProperty(value = "user_person")
    Integer getUserPerson();

    @JsonProperty(value = "test")
    Integer getTest();
}
