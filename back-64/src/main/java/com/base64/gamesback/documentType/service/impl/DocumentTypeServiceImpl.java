package com.base64.gamesback.documentType.service.impl;

import com.base64.gamesback.documentType.dto.DocumentTypeResponse;
import com.base64.gamesback.documentType.repository.DocumentTypeRepository;
import com.base64.gamesback.documentType.service.DocumentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }


    @Override
    public List<DocumentTypeResponse> getAllDocumentType() {
        return documentTypeRepository.getAllDocumentType();
    }
}
