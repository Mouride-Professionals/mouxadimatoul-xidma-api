package com.touba.backend.dto;

import com.touba.backend.model.Accueillant;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AccueillantDto {

    private Long id;
    private String nom;
    private String telephone;
    private ResidenceDto residence;
    private Set<ReservationDto> reservations;

    public static AccueillantDto fromEntity(Accueillant accueillant) {
        if (accueillant == null) {
            return null;
        }
        return AccueillantDto.builder()
                .id(accueillant.getId())
                .nom(accueillant.getNom())
                .telephone(accueillant.getTelephone())
                .residence(ResidenceDto.builder()
                        .id(accueillant.getResidence().getId())
                        .libelle(accueillant.getResidence().getLibelle())
                        .build())
                .build();
    }

    public static Accueillant toEntity(AccueillantDto dto) {
        if (dto == null) {
            return null;
        }
        Accueillant accueillant = new Accueillant();
        accueillant.setId(dto.getId());
        accueillant.setNom(dto.getNom());
        accueillant.setTelephone(dto.getTelephone());
        accueillant.setResidence(ResidenceDto.toEntity(dto.getResidence()));
        return accueillant;
    }

}
