package com.base64.gamesback.auth.user.dto.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface UserDoctorData {

    @JsonProperty(value = "user_id")
    String getUserId();

    @JsonProperty(value = "user_name")
    String getUserName();

    @JsonProperty(value = "administrator")
    Boolean getAdministrator();

    @JsonProperty(value = "profile_image")
    String getProfileImage();

    @JsonProperty(value = "doctor_name")
    String getDoctorName();

    @JsonProperty(value = "doctor_last_name")
    String getDoctorLastName();

    @JsonProperty(value = "doctor_image")
    String getDoctorImage();

    @JsonProperty(value = "doctor_address")
    String getDoctorAddress();

    @JsonProperty(value = "doctor_phone")
    String getDoctorPhone();

    @JsonProperty(value = "doctor_email")
    String getDoctorEmail();
}
