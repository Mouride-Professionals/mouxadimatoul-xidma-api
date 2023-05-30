package com.touba.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur extends AbstractModel {

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToOne()
    private Role role;

    private Boolean statut;

    private String prenom;

    private String nom;

    private String telephone;

}
