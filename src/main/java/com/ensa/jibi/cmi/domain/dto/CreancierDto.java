package com.ensa.jibi.cmi.domain.dto;

import com.ensa.jibi.cmi.domain.dto.creanceDto.CreanceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreancierDto {
    private Long id;
    private String nom;
    private String categorie;
    private String logoURL;
    private String description;
}
