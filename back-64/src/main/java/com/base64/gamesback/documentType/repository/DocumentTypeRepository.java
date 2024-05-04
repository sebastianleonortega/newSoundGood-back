package com.base64.gamesback.documentType.repository;

import com.base64.gamesback.documentType.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID>,DocumentTypeRepositoryCustom {
}
