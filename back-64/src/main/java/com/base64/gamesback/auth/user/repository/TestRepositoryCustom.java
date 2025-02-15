package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.TestResponse;

import java.util.List;
import java.util.UUID;

public interface TestRepositoryCustom {

    List<TestResponse> getTestByPerson(UUID personId);
}
