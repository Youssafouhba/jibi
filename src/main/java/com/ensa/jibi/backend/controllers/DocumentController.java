package com.ensa.jibi.backend.controllers;

import com.ensa.jibi.backend.domain.dto.DocumentDto;
import com.ensa.jibi.backend.domain.entities.Document;
import com.ensa.jibi.backend.services.DocumentService;
import com.ensa.jibi.backend.services.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final FileStorageService fileStorageService;

    @Autowired
    public DocumentController(DocumentService documentService, FileStorageService fileStorageService) {
        this.documentService = documentService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<Document> saveDocument(@RequestBody DocumentDto documentDto) {
        Document savedDocument = documentService.save(documentDto);
        return ResponseEntity.ok(savedDocument);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description)  throws Exception{

        // Handle file storage
        String fileUrl = fileStorageService.storeFile(file);

        // Create and save DocumentDto
        DocumentDto documentDto = new DocumentDto();
        documentDto.setDocUrl(fileUrl);
        documentDto.setDescription(description);

        Document savedDocument = documentService.save(documentDto);

        // Construct response with file URL
        Map<String, String> response = new HashMap<>();
        response.put("docUrl", fileUrl);
        response.put("documentId", savedDocument.getId().toString());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/client/{numTel}")
    public ResponseEntity<List<Document>> getDocumentsByClient(@PathVariable String numTel) {
        List<Document> documents = documentService.findByClient_NumTel(numTel);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/agent/{numTel}")
    public ResponseEntity<List<Document>> getDocumentsByAgent(@PathVariable String numTel) {
        List<Document> documents = documentService.findByAgent_NumTel(numTel);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            // Set default content type if unable to determine
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
