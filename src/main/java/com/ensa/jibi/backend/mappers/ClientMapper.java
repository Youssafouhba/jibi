package com.ensa.jibi.backend.mappers;

import com.ensa.jibi.backend.domain.dto.ClientDto;
import com.ensa.jibi.backend.domain.dto.DocumentDto;
import com.ensa.jibi.backend.domain.entities.Client;
import com.ensa.jibi.backend.domain.entities.Document;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper implements Mapper<Client, ClientDto> {
    private final ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientDto mapTo(Client client) {
        if (client == null) {
            return null;
        }
        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        List<DocumentDto> documentDtos = client.getDocuments().stream()
                .map(document -> modelMapper.map(document, DocumentDto.class))
                .collect(Collectors.toList());
        clientDto.setDocuments(documentDtos);
        return clientDto;
    }

    @Override
    public Client mapFrom(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        Client client = modelMapper.map(clientDto, Client.class);
        List<Document> documents = clientDto.getDocuments().stream()
                .map(documentDto -> {
                    Document document = modelMapper.map(documentDto, Document.class);
                    document.setClient(client); // Set the client reference to avoid nulls
                    return document;
                })
                .collect(Collectors.toList());
        client.setDocuments(documents);
        return client;
    }
}
