package com.base64.gamesback.genderType.service.impl;

import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.genderType.entity.GenderType;
import com.base64.gamesback.genderType.repository.GenderTypeRepository;
import com.base64.gamesback.genderType.service.GenderTypeSharedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class GenderTypeSharedServiceImpl implements GenderTypeSharedService {

    private final GenderTypeRepository genderTypeRepository;

    public GenderTypeSharedServiceImpl(GenderTypeRepository genderTypeRepository) {
        this.genderTypeRepository = genderTypeRepository;
    }

    @Override
    public GenderType getGenderTypeById(UUID genderTypeId) {
        return genderTypeRepository.findById(genderTypeId).orElseThrow(() -> new ResourceNotFoundException("No existe el tipo de genero buscado"));
    }


}
