package com.touba.backend.repository;

import com.touba.backend.model.Pavillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PavillonRepository extends JpaRepository<Pavillon, Long> {
    @Query("SELECT p FROM Pavillon p WHERE p.residence.id = :id")
    List<Pavillon> findAllByResidence(Long id);

    @Query("SELECT COUNT(p) FROM Pavillon p WHERE p.residence.id = :residence")
    Long countByResidence(Long residence);
}
