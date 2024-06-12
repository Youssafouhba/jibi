package com.ensa.jibi.backend.mappers;

import com.ensa.jibi.backend.domain.dto.AgentDto;
import com.ensa.jibi.backend.domain.dto.DocumentDto;
import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.entities.Document;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgentMapper implements Mapper<Agent, AgentDto> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AgentDto mapTo(Agent agent) {
        if (agent == null) {
            return null;
        }
        AgentDto agentDto = modelMapper.map(agent, AgentDto.class);
        List<DocumentDto> documentDtos = agent.getDocuments().stream()
                .map(document -> modelMapper.map(document, DocumentDto.class))
                .collect(Collectors.toList());
        agentDto.setDocuments(documentDtos);
        return agentDto;
    }

    @Override
    public Agent mapFrom(AgentDto agentDto) {
        if (agentDto == null) {
            return null;
        }
        Agent agent = modelMapper.map(agentDto, Agent.class);
        List<Document> documents = agentDto.getDocuments().stream()
                .map(documentDto -> {
                    Document document = modelMapper.map(documentDto, Document.class);
                    document.setAgent(agent); // Set the agent reference to avoid nulls
                    return document;
                })
                .collect(Collectors.toList());
        agent.setDocuments(documents);
        return agent;
    }
}
