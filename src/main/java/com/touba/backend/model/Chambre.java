package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chambre extends AbstractModel{

    private Integer nombrePlace;

    private String numero;

    private Integer niveau;

    @Column(unique = true)
    private String reference;

    private Boolean archive;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pavillon pavillon;

    @OneToMany(mappedBy = "chambre")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "chambres")
    private Set<Responsable> responsables;
}
