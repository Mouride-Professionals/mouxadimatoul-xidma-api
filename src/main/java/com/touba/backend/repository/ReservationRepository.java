package com.touba.backend.repository;

import com.touba.backend.dto.ChambreDto;
import com.touba.backend.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.stream.DoubleStream;

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

    @Query(
            "SELECT r FROM Reservation r WHERE r.chambre.pavillon.id = :pavillon " +
            "AND ((r.dateEntree BETWEEN :debut AND :fin) OR (r.dateSortie BETWEEN :debut AND :fin)) "
    )
    List<Reservation> findAllByPeriodeAndPavillon(Date debut, Date fin, Long pavillon);

    @Query(
            "SELECT r FROM Reservation r WHERE (r.evenement.id = :event OR -1 = :event) " +
            "AND (YEAR(r.dateEntree) = :year OR YEAR(r.dateSortie) = :year OR -1 = :year) " +
            "AND (-1 = :presence OR (r.presence = true AND 1 = :presence))"
    )
    Page<Reservation> findAll(Pageable pageable, int year, Long event, int presence);
}
