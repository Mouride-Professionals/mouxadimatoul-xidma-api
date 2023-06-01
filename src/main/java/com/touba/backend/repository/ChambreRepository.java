package com.touba.backend.repository;

import com.touba.backend.model.Chambre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    @Query("SELECT c FROM Chambre c WHERE c.pavillon.id = :pavillon")
    Page<Chambre> findAllByPavillon(Long pavillon, Pageable pageable);
}
