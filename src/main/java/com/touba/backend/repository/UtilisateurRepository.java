package com.touba.backend.repository;

import com.touba.backend.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);

    @Query("SELECT u FROM Utilisateur u WHERE " +
            "lower(u.prenom) LIKE %:search% " +
            "OR lower(u.nom) LIKE %:search% " +
            "OR u.telephone LIKE %:search% ")
    Page<Utilisateur> findAllBySearch(Pageable pageable, String search);
}
