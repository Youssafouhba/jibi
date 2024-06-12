package com.ensa.jibi.backend.domain.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AgentDto extends UserDto{
    private String cin;
    private String passeport;
    private LocalDate dateNaissance;
    private String adresse;
    private String email;
    private String numTel;

    private String numCommerce;
    private String numPatente;
    private List<DocumentDto> documents = new ArrayList<>();
}
