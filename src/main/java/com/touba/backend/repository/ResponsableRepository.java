package com.touba.backend.repository;

import com.touba.backend.model.Responsable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    @Query(
            "SELECT r FROM Responsable r WHERE (r.residence.id = :residence OR -1 = :residence) " +
            "AND (lower(r.nom) LIKE %:search% OR lower(r.prenom) LIKE %:search% " +
            "OR r.telephone LIKE %:search%)"
    )
    Page<Responsable> findAllByParams(Pageable pageable, String search, Long residence);
}
