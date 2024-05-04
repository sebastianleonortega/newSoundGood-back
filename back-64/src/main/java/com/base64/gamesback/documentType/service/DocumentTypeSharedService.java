package com.base64.gamesback.documentType.service;


import com.base64.gamesback.documentType.entity.DocumentType;

import java.util.UUID;

public interface DocumentTypeSharedService {
    DocumentType getDocumentType(UUID documentTypeId);
}
