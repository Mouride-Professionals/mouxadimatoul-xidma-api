package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pavillon extends AbstractModel{

    private String libelle;

    private Integer niveau;

    private Boolean archive;

    @ManyToOne
    private Residence residence;

    @OneToMany(mappedBy = "pavillon", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Chambre> chambres = new ArrayList<>();

}
