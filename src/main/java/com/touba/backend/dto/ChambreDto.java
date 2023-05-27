package com.touba.backend.dto;

import com.touba.backend.model.Chambre;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChambreDto {

    private Long id;

     private Integer nombrePlace;

    private String description;

    private String numero;

    private PavillonDto pavillon;

    public static ChambreDto fromEntity(Chambre chambre) {
        if (chambre == null) {
            return null;
        }
        return ChambreDto.builder()
                .id(chambre.getId())
                .nombrePlace(chambre.getNombrePlace())
                .description(chambre.getDescription())
                .numero(chambre.getNumero())
                .pavillon(PavillonDto.fromEntity(chambre.getPavillon()))
                .build();
    }

    public static Chambre toEntity(ChambreDto dto) {
        if (dto == null) {
            return null;
        }
        Chambre chambre = new Chambre();
        chambre.setId(dto.getId());
        chambre.setNombrePlace(dto.getNombrePlace());
        chambre.setDescription(dto.getDescription());
        chambre.setNumero(dto.getNumero());
        chambre.setPavillon(PavillonDto.toEntity(dto.getPavillon()));
        return chambre;
    }

}
