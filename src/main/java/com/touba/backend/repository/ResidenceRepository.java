package com.touba.backend.repository;

import com.touba.backend.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    @Query("SELECT r FROM Residence r WHERE r.responsable.username LIKE %:responsable%")
    Optional<Residence> findByResponsable(String responsable);
}
