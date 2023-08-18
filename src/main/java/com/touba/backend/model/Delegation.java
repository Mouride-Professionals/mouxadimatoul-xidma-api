package com.touba.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delegation extends AbstractModel{

    private String nom;

    private Integer nombre;

    @OneToMany(mappedBy = "delegation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invite> invites;

}
