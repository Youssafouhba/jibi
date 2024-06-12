package com.ensa.jibi.cmi.domain.entities;

import com.ensa.jibi.backend.domain.entities.Champ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Formulaire {
    private String type;
    private List<Champ> champs;
}
