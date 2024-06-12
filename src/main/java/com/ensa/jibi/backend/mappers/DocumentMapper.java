package com.ensa.jibi.backend.mappers;

import com.ensa.jibi.backend.domain.dto.DocumentDto;
import com.ensa.jibi.backend.domain.entities.Document;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper implements Mapper<Document, DocumentDto> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DocumentDto mapTo(Document document) {
        return modelMapper.map(document, DocumentDto.class);
    }

    @Override
    public Document mapFrom(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }
}
