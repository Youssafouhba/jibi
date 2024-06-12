package com.ensa.jibi.backend.repositories;

import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.entities.Client;
import com.ensa.jibi.backend.domain.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByAgent_NumTel(String numTel);
    List<Document> findByClient_NumTel(String numTel);

}
