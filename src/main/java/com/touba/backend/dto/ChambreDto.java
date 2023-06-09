package com.touba.backend.dto;

import com.touba.backend.model.Chambre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChambreDto {

    private Long id;

    private Integer nombrePlace;

    private String description;

    private String numero;

    private String reference;

    private PavillonDto pavillon;

    private Long placeReservee;

    private List<ReservationDto> reservations;

    public ChambreDto(Long id, Integer nombrePlace, String numero, Long placeReservee) {
        this.id = id;
        this.nombrePlace = nombrePlace;
        this.numero = numero;
        this.placeReservee = placeReservee;
    }

    public static ChambreDto fromEntity(Chambre chambre) {
        if (chambre == null) {
            return null;
        }
        return ChambreDto.builder()
                .id(chambre.getId())
                .nombrePlace(chambre.getNombrePlace())
                .description(chambre.getDescription())
                .reference(chambre.getReference())
                .numero(chambre.getNumero())
                .placeReservee(0L)
                .pavillon(chambre.getPavillon() != null ? PavillonDto.builder()
                        .id(chambre.getPavillon().getId())
                        .libelle(chambre.getPavillon().getLibelle())
                        .build() : null)
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
        chambre.setReference(dto.getReference());
        chambre.setPavillon(PavillonDto.toEntity(dto.getPavillon()));
        return chambre;
    }

}
