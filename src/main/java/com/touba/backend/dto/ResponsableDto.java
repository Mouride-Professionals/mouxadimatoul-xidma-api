package com.touba.backend.dto;

import com.touba.backend.model.Responsable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ResponsableDto {

    private Long id;
    private String prenom;
    private String nom;
    private String telephone;
    private ResidenceDto residence;
    private Set<ChambreDto> chambres;

    public static ResponsableDto fromEntity(Responsable responsable) {
        if (responsable == null) {
            return null;
        }
        return ResponsableDto.builder()
                .id(responsable.getId())
                .prenom(responsable.getPrenom())
                .nom(responsable.getNom())
                .telephone(responsable.getTelephone())
                .residence(ResidenceDto.fromEntity(responsable.getResidence()))
//                .chambres(
//                        responsable.getChambres() != null
//                            ? responsable.getChambres().stream().map(
//                                    chambre -> ChambreDto.builder()
//                                            .id(chambre.getId())
//                                            .reference(chambre.getReference())
//                                            .pavillon(PavillonDto.builder()
//                                                    .id(chambre.getPavillon().getId())
//                                                    .libelle(chambre.getPavillon().getLibelle())
//                                                    .residence(ResidenceDto.builder()
//                                                            .id(chambre.getPavillon().getResidence().getId())
//                                                            .libelle(chambre.getPavillon().getResidence().getLibelle())
//                                                            .build())
//                                                    .build())
//                                            .build()
//                        ).collect(Collectors.toSet()) : new HashSet<>()
//                )
                .build();
    }

    public static Responsable toEntity(ResponsableDto dto) {
        if (dto == null) {
            return null;
        }
        Responsable responsable = new Responsable();
        responsable.setId(dto.getId());
        responsable.setPrenom(dto.getPrenom());
        responsable.setNom(dto.getNom());
        responsable.setTelephone(dto.getTelephone());
        responsable.setResidence(ResidenceDto.toEntity(dto.getResidence()));
        responsable.setChambres(
                dto.getChambres() != null
                    ? dto.getChambres().stream().map(ChambreDto::toEntity).collect(Collectors.toSet()) : new HashSet<>()

        );
        return responsable;
    }

}
