package com.ensa.jibi.cmi.domain.entities.creance;

import com.ensa.jibi.cmi.domain.entities.Impaye;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@DiscriminatorValue("FACTURE")
public class Facture extends Creance{
    private Long numFacture;
    //num Facture = 203445 => 3 impayes
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Impaye> impayes = new ArrayList<>();

}
