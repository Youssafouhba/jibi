package com.ensa.jibi.cmi.controllers;


import com.ensa.jibi.cmi.services.impl.FormulaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formulaire")
@CrossOrigin(origins = "http://localhost:4200")

public class FormulaireController {
    @Autowired
    private FormulaireServiceImpl formulaireService;

    @GetMapping("/{type}")
    public Object getFormulaire(@PathVariable String type) {
        return formulaireService.getFormulaireByCReance(type);
    }
}
