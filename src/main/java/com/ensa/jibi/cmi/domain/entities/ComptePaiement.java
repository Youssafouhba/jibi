package com.ensa.jibi.cmi.domain.entities;

import com.ensa.jibi.cmi.exceptions.InsufficientBalanceException;
import com.ensa.jibi.cmi.domain.entities.creance.Creance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ComptePaiement {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;//numTelephone
    private Double solde;

    @OneToMany(mappedBy = "compte" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ConfirmationPaiement> confirmationPaiements = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "numTel", referencedColumnName = "numTel")
//    private Client client;

    public void payer(Double montant, Creance creance) throws InsufficientBalanceException {
        if (this.solde >= montant) {
            this.solde -= montant;
            ConfirmationPaiement confirmation = new ConfirmationPaiement();
            confirmation.setCompte(this);
            confirmation.setMontant(montant);
            confirmation.setCreance(creance);
            confirmation.setDate(LocalDate.now());
            this.confirmationPaiements.add(confirmation);
        } else {
            throw new InsufficientBalanceException("Solde insuffisant pour effectuer le paiement.");
        }
    }


}
