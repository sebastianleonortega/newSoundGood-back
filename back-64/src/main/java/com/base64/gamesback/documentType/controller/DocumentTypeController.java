package com.base64.gamesback.documentType.controller;

import com.base64.gamesback.documentType.dto.DocumentTypeResponse;
import com.base64.gamesback.documentType.service.DocumentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/security/document_type")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping("/")
    @Operation(description = "Get All document Type")
    @ApiResponse(responseCode = "200",description = "OK")
    public ResponseEntity<List<DocumentTypeResponse>> getAllDocumentType(){
        return new ResponseEntity<>(documentTypeService.getAllDocumentType(), HttpStatus.OK);
    }
}
