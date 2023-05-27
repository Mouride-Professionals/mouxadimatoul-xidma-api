package com.touba.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne()
    private Pavillon pavillon;


}
