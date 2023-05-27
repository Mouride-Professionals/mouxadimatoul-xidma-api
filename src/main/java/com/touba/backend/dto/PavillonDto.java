package com.touba.backend.dto;

import com.touba.backend.model.Pavillon;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class PavillonDto {

    private Long id;

    private String libelle;

    private Integer niveau;

    private ResidenceDto residence;

    private Set<ChambreDto> chambres;

    public static PavillonDto fromEntity(Pavillon pavillon) {
        if (pavillon == null) {
            return null;
        }
        return PavillonDto.builder()
                .id(pavillon.getId())
                .libelle(pavillon.getLibelle())
                .niveau(pavillon.getNiveau())
                .residence(ResidenceDto.fromEntity(pavillon.getResidence()))
                .chambres(pavillon.getChambres().stream().map(ChambreDto::fromEntity).collect(Collectors.toSet()))
                .build();
    }

    public static Pavillon toEntity(PavillonDto dto) {
        if (dto == null) {
            return null;
        }
        Pavillon pavillon = new Pavillon();
        pavillon.setId(dto.getId());
        pavillon.setLibelle(dto.getLibelle());
        pavillon.setNiveau(dto.getNiveau());
        pavillon.setResidence(ResidenceDto.toEntity(dto.getResidence()));
        pavillon.setChambres(dto.getChambres().stream().map(ChambreDto::toEntity).collect(Collectors.toSet()));
        return pavillon;
    }

}
