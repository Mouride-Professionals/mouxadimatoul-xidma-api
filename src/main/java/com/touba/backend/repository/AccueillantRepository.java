package com.touba.backend.repository;

import com.touba.backend.model.Accueillant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccueillantRepository extends JpaRepository<Accueillant, Long> {
    @Query(
            "SElECT a FROM Accueillant a WHERE (a.residence.id = :idRes OR -1 = :idRes) " +
            "AND (lower(a.nom) LIKE %:search% " +
            "OR a.telephone LIKE %:search%)"
    )
    Page<Accueillant> findAllByParams(Pageable pageable, Long idRes, String search);
}
