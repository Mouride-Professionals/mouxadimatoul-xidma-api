package com.touba.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evenement extends AbstractModel{

    private String libelle;

    @OneToMany(mappedBy = "evenement")
    private List<Reservation> reservations = new ArrayList<>();

    public Evenement(String libelle) {
        this.libelle = libelle;
    }

}
