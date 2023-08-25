package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accueillant extends AbstractModel{

    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;

    @ManyToOne
    private Residence residence;

    @OneToMany(mappedBy = "accueillant")
    private Set<Reservation> reservations;

}
