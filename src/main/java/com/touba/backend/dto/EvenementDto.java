package com.touba.backend.dto;

import com.touba.backend.model.Evenement;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EvenementDto {

    private Long id;

    private String libelle;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private List<ReservationDto> reservations;

    public static EvenementDto fromEntity(Evenement evenement) {
        if (evenement == null) {
            return null;
        }
        return EvenementDto.builder()
                .id(evenement.getId())
                .libelle(evenement.getLibelle())
                .dateDebut(evenement.getDateDebut())
                .dateFin(evenement.getDateFin())
                .build();
    }

    public static Evenement toEntity(EvenementDto dto) {
        if (dto == null) {
            return null;
        }
        Evenement evenement = new Evenement();
        evenement.setId(dto.getId());
        evenement.setLibelle(dto.getLibelle());
        evenement.setDateDebut(dto.getDateDebut());
        evenement.setDateFin(dto.getDateFin());
        return evenement;
    }

}
