package com.base64.gamesback.genderType.service;


import com.base64.gamesback.genderType.entity.GenderType;

import java.util.UUID;

public interface GenderTypeSharedService {

    GenderType getGenderTypeById(UUID genderTypeId);
}
