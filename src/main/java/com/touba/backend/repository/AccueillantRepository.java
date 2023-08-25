package com.touba.backend.repository;

import com.touba.backend.model.Accueillant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccueillantRepository extends JpaRepository<Accueillant, Long> {
    @Query(
            "SElECT a FROM Accueillant a WHERE (a.residence.id = :idRes OR -1 = :idRes) " +
            "AND (lower(a.utilisateur.nom) LIKE %:search% OR lower(a.utilisateur.prenom) LIKE %:search% " +
            "OR a.utilisateur.telephone LIKE %:search%)"
    )
    Page<Accueillant> findAllByParams(Pageable pageable, Long idRes, String search);

    @Query("SELECT a FROM Accueillant a WHERE a.utilisateur.username LIKE %:username%")
    Optional<Accueillant> findByUsername(String username);
}
