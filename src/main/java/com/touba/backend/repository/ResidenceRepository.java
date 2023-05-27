package com.touba.backend.repository;

import com.touba.backend.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
}
