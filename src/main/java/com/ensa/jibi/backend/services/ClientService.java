package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.dto.ClientDto;
import com.ensa.jibi.backend.domain.entities.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ResponseEntity<?> addClient(ClientDto clientDto,Long agentId);
    ClientDto updateClient(Long id, ClientDto clientDto);
    void deleteClient(Long id);
    ClientDto getClientById(Long id);
    List<ClientDto> getAllClients();
    List<ClientDto> getClientsByAgentId(Long agentId);
    void deleteByNumTel(String id);
}
