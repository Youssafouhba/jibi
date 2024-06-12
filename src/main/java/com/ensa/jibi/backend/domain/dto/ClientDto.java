package com.ensa.jibi.backend.domain.dto;

import com.ensa.jibi.backend.domain.entities.Document;
import com.ensa.jibi.backend.domain.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto extends UserDto{
    private String email;
    private String numTel;
    private ClientType clientType;
    private List<DocumentDto> documents = new ArrayList<>();

}