package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID>, TestRepositoryCustom {
}
