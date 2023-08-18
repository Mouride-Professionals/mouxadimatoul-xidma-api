package com.touba.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accueillant extends AbstractModel{

    private String nom;

    private String telephone;

    @ManyToOne
    private Residence residence;

    @OneToMany(mappedBy = "accueillant")
    private Set<Reservation> reservations;

}
