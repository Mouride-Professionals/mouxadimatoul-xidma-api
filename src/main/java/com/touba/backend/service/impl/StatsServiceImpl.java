package com.touba.backend.service.impl;

import com.touba.backend.dto.ChambreDto;
import com.touba.backend.dto.stats.ChambreDispoDto;
import com.touba.backend.dto.stats.TotalStatsDto;
import com.touba.backend.repository.ChambreRepository;
import com.touba.backend.repository.PavillonRepository;
import com.touba.backend.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl {

    private final PavillonRepository pavillonRepository;
    private final ChambreRepository chambreRepository;
    private final ReservationRepository reservationRepository;

    public TotalStatsDto getStatsByResidence(Long residence) {
        Long countPavillons = pavillonRepository.countByResidence(residence);
        Long countChambres = chambreRepository.countByResidence(residence);
        Long countDelegations = reservationRepository.countDelegationByResidence(residence);
        Long countReservations = reservationRepository.countByResidence(residence);

        return TotalStatsDto.builder()
                .pavillons(countPavillons != null ? countPavillons : 0)
                .chambres(countChambres != null ? countChambres : 0)
                .delegations(countDelegations != null ? countDelegations : 0)
                .reservations(countReservations != null ? countReservations : 0)
                .build();
    }

    public List<ChambreDispoDto> getChambreDispoByResidence(Long residence) {
        Date debut = new Date();
        Date fin = new Date(debut.getTime() + (1000L * 60 * 60 * 24 * 30));
        List<ChambreDto> chambresReservees = reservationRepository.findAllByPeriodAndResidence(debut, fin, residence);
        List<Long> idIndisponibles = chambresReservees.stream()
                .filter(c -> c.getNombrePlace() <= c.getPlaceReservee())
                .map(
                        ChambreDto::getId
                ).toList();
        if (idIndisponibles.isEmpty()) {
            idIndisponibles = new ArrayList<>(List.of(0L));
        }
        return chambreRepository.findAllByResidenceDispo(residence, idIndisponibles);
    }

}
