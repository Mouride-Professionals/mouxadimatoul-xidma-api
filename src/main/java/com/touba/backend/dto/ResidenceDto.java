package com.touba.backend.dto;

import com.touba.backend.model.Residence;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
public class ResidenceDto {

    private Long id;

    private String libelle;

    private String description;

    private String adresse;

    private String telephoneResidence;

    private Boolean archive;

    private RessourceDto image;

    private UtilisateurDto responsable;

    private List<PavillonDto> pavillons;

    public static ResidenceDto fromEntity(Residence residence) {
        if (residence == null) {
            return null;
        }
        return ResidenceDto.builder()
                .id(residence.getId())
                .libelle(residence.getLibelle())
                .description(residence.getDescription())
                .adresse(residence.getAdresse())
                .telephoneResidence(residence.getTelephoneResidence())
                .image(RessourceDto.fromEntity(residence.getImage()))
                .responsable(UtilisateurDto.fromEntity(residence.getResponsable()))
                .pavillons(residence.getPavillons().stream().map(PavillonDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public static Residence toEntity(ResidenceDto dto) {
        if (dto == null) {
            return null;
        }
        Residence residence = new Residence();
        residence.setId(dto.getId());
        residence.setLibelle(dto.getLibelle());
        residence.setDescription(dto.getDescription());
        residence.setAdresse(dto.getAdresse());
        residence.setTelephoneResidence(dto.getTelephoneResidence());
        residence.setImage(RessourceDto.toEntity(dto.getImage()));
        residence.setResponsable(UtilisateurDto.toEntity(dto.getResponsable()));
        return residence;
    }

}
