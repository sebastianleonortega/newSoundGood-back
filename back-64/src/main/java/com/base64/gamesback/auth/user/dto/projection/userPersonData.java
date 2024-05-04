package com.base64.gamesback.auth.user.dto.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface userPersonData {

    @JsonProperty(value = "user_id")
    String getUserId();

    @JsonProperty(value = "user_name")
    String getUserName();

    @JsonProperty(value = "administrator")
    Boolean getAdministrator();

    @JsonProperty(value = "profile_image")
    String getProfileImage();

    @JsonProperty(value = "patient_name")
    String getPersonName();

    @JsonProperty(value = "patient_last_name")
    String getPersonLastName();

    @JsonProperty(value = "patient_document")
    String getPersonDocument();

    @JsonProperty(value = "patient_address")
    String getPersonAddress();

    @JsonProperty(value = "patient_phone")
    String getPersonPhone();

    @JsonProperty(value = "patient_email")
    String getPersonEmail();
}
