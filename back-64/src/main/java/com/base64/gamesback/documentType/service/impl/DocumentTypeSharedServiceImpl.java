package com.base64.gamesback.documentType.service.impl;

import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.documentType.entity.DocumentType;
import com.base64.gamesback.documentType.repository.DocumentTypeRepository;
import com.base64.gamesback.documentType.service.DocumentTypeSharedService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentTypeSharedServiceImpl implements DocumentTypeSharedService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeSharedServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public DocumentType getDocumentType(UUID documentTypeId) {
        return documentTypeRepository.findById(documentTypeId).orElseThrow(()-> new ResourceNotFoundException("tipo de documento no disponible"));
    }
}
