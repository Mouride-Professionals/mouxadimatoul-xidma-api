package com.touba.backend.repository;

import com.touba.backend.dto.ChambreDto;
import com.touba.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    @Query(
//            "SELECT r FROM Reservation r WHERE r.chambre.pavillon.residence.id = :residence " +
//            "AND ((r.dateEntree >= :debut AND r.dateEntree <= :fin) OR (r.dateSortie >= :fin AND r.dateSortie <= :fin))"
//    )
    @Query(
            "SELECT new com.touba.backend.dto.ChambreDto(r.chambre.id, r.chambre.nombrePlace, r.chambre.numero, count(r)) " +
            "FROM Reservation r WHERE r.chambre.pavillon.residence.id = :residence " +
            "AND ((r.dateEntree BETWEEN :debut AND :fin) OR (r.dateSortie BETWEEN :debut AND :fin)) " +
            "GROUP BY r.chambre.id, r.chambre.nombrePlace, r.chambre.numero"
    )
    List<ChambreDto> findAllByPeriodAndResidence(Date debut, Date fin, Long residence);

}
