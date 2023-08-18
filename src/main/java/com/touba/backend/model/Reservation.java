package com.touba.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends AbstractModel{

    private Date dateEntree;

    private Date dateSortie;

    private Date dateSortieProvisoire;

    private Boolean statut;

    @ManyToOne()
    private Evenement evenement;

    @ManyToOne()
    private Chambre chambre;

    @ManyToOne()
    private Invite invite;

    @ManyToOne()
    private Accueillant accueillant;

    private Boolean presence;

}
