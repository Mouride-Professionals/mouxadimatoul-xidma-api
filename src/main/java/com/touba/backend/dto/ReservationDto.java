package com.touba.backend.dto;

import com.touba.backend.model.Reservation;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationDto {

    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Date dateEntree;
    private Date dateSortie;
    private Date dateSortieProvisoire;
    private Boolean statut;
    private EvenementDto evenement;
    private ChambreDto chambre;
    private InviteDto invite;
    private AccueillantDto accueillant;
    private Boolean presence;

    public static ReservationDto fromEntity(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        return ReservationDto.builder()
                .id(reservation.getId())
                .createdAt(reservation.getCreatedAt())
                .updatedAt(reservation.getUpdatedAt())
                .dateEntree(reservation.getDateEntree())
                .dateSortie(reservation.getDateSortie())
                .dateSortieProvisoire(reservation.getDateSortieProvisoire())
                .statut(reservation.getStatut())
                .evenement(EvenementDto.fromEntity(reservation.getEvenement()))
                .chambre(ChambreDto.fromEntity(reservation.getChambre()))
                .invite(InviteDto.fromEntity(reservation.getInvite()))
                .presence(reservation.getPresence())
                .accueillant(AccueillantDto.fromEntity(reservation.getAccueillant()))
                .build();
    }

    public static Reservation toEntity(ReservationDto dto) {
        if (dto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setCreatedAt(dto.getCreatedAt());
        reservation.setUpdatedAt(dto.getUpdatedAt());
        reservation.setDateEntree(dto.getDateEntree());
        reservation.setDateSortie(dto.getDateSortie());
        reservation.setDateSortieProvisoire(dto.getDateSortieProvisoire());
        reservation.setStatut(dto.getStatut());
        reservation.setEvenement(EvenementDto.toEntity(dto.getEvenement()));
        reservation.setChambre(ChambreDto.toEntity(dto.getChambre()));
        reservation.setInvite(InviteDto.toEntity(dto.getInvite()));
        reservation.setPresence(dto.getPresence());
        reservation.setAccueillant(AccueillantDto.toEntity(dto.getAccueillant()));
        return reservation;
    }

}
