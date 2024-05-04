package com.base64.gamesback.genderType.repository;

import com.base64.gamesback.genderType.entity.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenderTypeRepository extends JpaRepository<GenderType, UUID>, GenderTypeRepositoryCustom {
}
