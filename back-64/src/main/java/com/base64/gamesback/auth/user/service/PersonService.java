package com.base64.gamesback.auth.user.service;

import com.base64.gamesback.auth.user.dto.DoctorDto;
import com.base64.gamesback.auth.user.dto.PersonDto;
import com.base64.gamesback.auth.user.dto.PersonUpdateRequest;
import com.base64.gamesback.auth.user.entity.User;

import java.util.UUID;

public interface PersonService {

    Boolean existPersonByEmail(String personEmail);

    Boolean existPersonByDocument(String personDocument);

    void registerPerson(User user, PersonDto personDto);

    void updatePerson(PersonUpdateRequest request, User user);
}
