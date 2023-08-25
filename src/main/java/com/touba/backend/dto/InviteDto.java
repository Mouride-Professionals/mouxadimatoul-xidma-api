package com.touba.backend.dto;

import com.touba.backend.model.Delegation;
import com.touba.backend.model.Invite;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class InviteDto {

    private Long id;

    private String prenom;

    private String nom;

    private String telephone;

    private String adresse;

    private String email;

    private Boolean estResponsable;

    private DelegationDto delegation;

    private List<ReservationDto> reservations;

    public static InviteDto fromEntity(Invite invite) {
        if (invite == null) {
            return null;
        }
        return InviteDto.builder()
                .id(invite.getId())
                .prenom(invite.getPrenom())
                .nom(invite.getNom())
                .telephone(invite.getTelephone())
                .adresse(invite.getAdresse())
                .email(invite.getEmail())
                .estResponsable(invite.getEstResponsable())
                .delegation(invite.getDelegation() == null ? null : DelegationDto.builder()
                        .id(invite.getDelegation().getId())
                        .nom(invite.getDelegation().getNom())
                        .build())
                .build();
    }

    public static Invite toEntity(InviteDto dto) {
        if (dto == null) {
            return null;
        }
        Invite invite = new Invite();
        invite.setId(dto.getId());
        invite.setPrenom(dto.getPrenom());
        invite.setNom(dto.getNom());
        invite.setTelephone(dto.getTelephone());
        invite.setAdresse(dto.getAdresse());
        invite.setEmail(dto.getEmail());
        invite.setEstResponsable(dto.getEstResponsable());
        Delegation delegation = new Delegation();
        delegation.setId(dto.getDelegation().getId());
        invite.setDelegation(delegation);
        return invite;
    }

}
