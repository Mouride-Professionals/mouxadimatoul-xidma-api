package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Residence extends AbstractModel{

    private String libelle;

    private String adresse;

    private String telephoneResidence;

    private Boolean archive;

    @OneToMany(mappedBy = "residence", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Accueillant> accueillants;

    @OneToOne(cascade = CascadeType.ALL)
    private Ressource image;

    @OneToOne()
    private Utilisateur responsable;

    @OneToMany(mappedBy = "residence", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Pavillon> pavillons = new ArrayList<>();

}
