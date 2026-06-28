package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assignment extends AbstractModel {

    @ManyToOne(optional = false)
    private Utilisateur agent;

    @ManyToOne(optional = false)
    private Residence residence;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "assignment_responsibilities", joinColumns = @JoinColumn(name = "assignment_id"))
    @Column(name = "responsibility")
    private Set<Responsibility> responsibilities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RotationSlot> rotationSlots = new ArrayList<>();

    private LocalDate startDate;

    private LocalDate endDate;
}
