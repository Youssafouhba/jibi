package com.ensa.jibi.cmi.services;


import com.ensa.jibi.cmi.domain.entities.Formulaire;

public interface FormulaireService {
    Formulaire getFormulaireByCReance(String type);
}
