package com.base64.gamesback.genderType.service.impl;

import com.base64.gamesback.genderType.dto.GenderTypeResponse;
import com.base64.gamesback.genderType.repository.GenderTypeRepository;
import com.base64.gamesback.genderType.service.GenderTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GenderTypeServiceImpl implements GenderTypeService {

    private final GenderTypeRepository genderTypeRepository;

    public GenderTypeServiceImpl(GenderTypeRepository genderTypeRepository) {
        this.genderTypeRepository = genderTypeRepository;
    }

    @Override
    public List<GenderTypeResponse> getAllGenderTypes() {
        return genderTypeRepository.getAllGenderTypes();
    }
}
