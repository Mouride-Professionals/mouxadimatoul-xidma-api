package com.touba.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pavillon extends AbstractModel{

    private String libelle;

    private Integer niveau;

    @ManyToOne()
    private Residence residence;

    @OneToMany(mappedBy = "pavillon")
    private Set<Chambre> chambres;

}
