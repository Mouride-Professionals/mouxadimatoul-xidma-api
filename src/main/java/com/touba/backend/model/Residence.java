package com.touba.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Residence extends AbstractModel{

    private String libelle;

    private String description;

    private String adresse;

    private String telephoneResidence;

    @OneToOne(cascade = CascadeType.ALL)
    private Ressource image;

    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur responsable;

    @OneToMany(mappedBy = "residence")
    private Set<Pavillon> pavillons;

}
