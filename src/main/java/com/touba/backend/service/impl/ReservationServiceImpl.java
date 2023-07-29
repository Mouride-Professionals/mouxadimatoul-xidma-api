package com.touba.backend.service.impl;

import com.touba.backend.dto.ChambreDto;
import com.touba.backend.dto.InviteDto;
import com.touba.backend.dto.ReservationDto;
import com.touba.backend.dto.request.ReservationRequestBody;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.model.Invite;
import com.touba.backend.model.Reservation;
import com.touba.backend.repository.InviteRepository;
import com.touba.backend.repository.ReservationRepository;
import com.touba.backend.service.ReservationService;
import com.touba.backend.validator.ReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InviteRepository inviteRepository;

    @Override
    public List<ReservationDto> save(ReservationRequestBody request) {
        List<String> errors = ReservationValidator.validateBody(request);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("Le formulaire de réservation est invalid", errors);
        }
        List<Reservation> reservations = new ArrayList<>();
        request.getInvites().forEach(inv -> {
            Reservation reservation = new Reservation();
            reservation.setDateEntree(request.getPeriod().getEntree());
            reservation.setDateSortie(request.getPeriod().getSortie());
            reservation.setDateSortieProvisoire(request.getPeriod().getSortie());
            reservation.setEvenement(request.getEvenement());
            reservation.setChambre(ChambreDto.toEntity(inv.getChambre()));
            reservation.setInvite(
                    inviteRepository.findByTelephone(inv.getTelephone()).orElse(
                            InviteDto.toEntity(
                                    InviteDto.builder()
                                            .prenom(inv.getPrenom())
                                            .nom(inv.getNom())
                                            .telephone(inv.getTelephone())
                                            .email(inv.getEmail())
                                            .adresse(inv.getAdresse())
                                            .build()
                            )
                    )
            );
            reservations.add(reservation);
        });
        return reservationRepository.saveAll(reservations).stream().map(ReservationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ReservationDto findById(Long id) {
        return null;
    }

    @Override
    public List<ReservationDto> findByPeriodAndPavillon(Date debut, Date fin, Long pavillon) {
        return reservationRepository.findAllByPeriodeAndPavillon(debut, fin, pavillon).stream().map(ReservationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<ReservationDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by("dateEntree").ascending());
        return reservationRepository.findAll(pageable).map(ReservationDto::fromEntity);
    }
}
