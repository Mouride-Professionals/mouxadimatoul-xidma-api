package com.touba.backend.repository;

import com.touba.backend.model.Pavillon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PavillonRepository extends JpaRepository<Pavillon, Long> {
}
