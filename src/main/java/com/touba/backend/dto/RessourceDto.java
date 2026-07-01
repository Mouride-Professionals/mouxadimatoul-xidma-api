package com.touba.backend.dto;

import com.touba.backend.model.Ressource;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RessourceDto {

    private Long id;

    private String type;

    private String nom;

    private byte[] fichier;

    public static RessourceDto fromEntity(Ressource ressource) {
        if (ressource == null) {
            return null;
        }

        return RessourceDto.builder()
                .id(ressource.getId())
                .type(ressource.getType())
                .nom(ressource.getNom())
                .build();
    }

    public static Ressource toEntity(RessourceDto dto) {
        if (dto == null) {
            return null;
        }
        Ressource ressource = new Ressource();
        ressource.setId(dto.getId());
        ressource.setNom(dto.getNom());
        ressource.setType(dto.getType());
        ressource.setFichier(dto.getFichier());

        return ressource;
    }
}
