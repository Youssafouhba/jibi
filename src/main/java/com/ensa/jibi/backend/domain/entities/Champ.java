package com.ensa.jibi.backend.domain.entities;

import com.ensa.jibi.cmi.domain.enums.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Champ {
    private String nom;
    private FieldType type;
    private List<String> options;

    public Champ(String nom, FieldType type) {
        this.nom = nom;
        this.type = type;
    }

}
