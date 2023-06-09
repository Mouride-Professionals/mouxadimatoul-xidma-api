package com.touba.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invite extends AbstractModel{

    @Column(length = 40)
    private String prenom;

    @Column(length = 20)
    private String nom;

    @Column(length = 15)
    private String telephone;

    @Column(length = 90)
    private String adresse;

    @Column(length = 90)
    private String email;

    @OneToMany(mappedBy = "invite")
    private List<Reservation> reservations = new ArrayList<>();

}
