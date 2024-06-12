package com.ensa.jibi.cmi.domain.entities.creance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@DiscriminatorValue("DONATION")
public class Donation extends Creance{
    private String nomDonateur;
    private Double montant;
}
