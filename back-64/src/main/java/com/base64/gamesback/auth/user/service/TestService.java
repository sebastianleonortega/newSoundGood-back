package com.base64.gamesback.auth.user.service;

import com.base64.gamesback.auth.user.dto.TestRequest;
import com.base64.gamesback.auth.user.dto.TestResponse;
import com.base64.gamesback.auth.user.entity.Test;

import java.util.List;
import java.util.UUID;

public interface TestService {

    void registerTestToPerson(TestRequest testRequest);

    void deleteTestToPerson(UUID testId);

    Test getTestById(UUID testId);

    List<TestResponse> getTestByPerson(UUID personId);
}
