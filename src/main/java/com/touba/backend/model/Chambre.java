package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chambre extends AbstractModel{

    private Integer nombrePlace;

    private String description;

    private String numero;

    @Column(unique = true)
    private String reference;

    private Boolean archive;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pavillon pavillon;

}
