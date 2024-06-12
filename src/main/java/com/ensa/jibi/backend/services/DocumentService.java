package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.dto.DocumentDto;
import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.entities.Client;
import com.ensa.jibi.backend.domain.entities.Document;
import com.ensa.jibi.backend.mappers.DocumentMapper;
import com.ensa.jibi.backend.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentMapper documentMapper, DocumentRepository documentRepository) {
        this.documentMapper = documentMapper;
        this.documentRepository = documentRepository;
    }

    public Document save(DocumentDto document) {
        return documentRepository.save(
                documentMapper.mapFrom(document));
    }
    public List<Document> findByClient_NumTel(String client) {
        return documentRepository.findByClient_NumTel(client);
    }

    public List<Document> findByAgent_NumTel(String agent) {
        return documentRepository.findByAgent_NumTel(agent);
    }

}
