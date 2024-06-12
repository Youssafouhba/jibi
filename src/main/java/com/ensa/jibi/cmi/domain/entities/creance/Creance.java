package com.ensa.jibi.cmi.domain.entities.creance;

import com.ensa.jibi.cmi.domain.entities.ConfirmationPaiement;
import com.ensa.jibi.cmi.domain.entities.Creancier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Creance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "creancier_id")
    private Creancier creancier;

    @OneToMany(mappedBy = "creance" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConfirmationPaiement> confirmationPaiements = new ArrayList<>();


}
