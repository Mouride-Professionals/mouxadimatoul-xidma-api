package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Responsable extends AbstractModel{

    private String prenom;

    private String nom;

    private String telephone;

    @ManyToOne
    private Residence residence;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Chambre> chambres;

}
