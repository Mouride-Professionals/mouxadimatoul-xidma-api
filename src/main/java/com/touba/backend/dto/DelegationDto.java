package com.touba.backend.dto;

import com.touba.backend.model.Delegation;
import com.touba.backend.model.Invite;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
public class DelegationDto {
    private Long id;
    private String nom;
    private Integer nombre;
    private InviteDto chef;
    private List<InviteDto> invites;

    public static DelegationDto fromEntity(Delegation delegation) {
        if (delegation == null) {
            return null;
        }
        Optional<Invite> chef = delegation.getInvites().stream().filter(inv -> inv.getEstResponsable() != null && inv.getEstResponsable()).findFirst();
        List<Invite> others = delegation.getInvites().stream().filter(inv ->  inv.getEstResponsable() == null || !inv.getEstResponsable()).toList();
        return DelegationDto.builder()
                .id(delegation.getId())
                .nom(delegation.getNom())
                .nombre(delegation.getNombre())
                .chef(chef.map(InviteDto::fromEntity).orElse(null))
                .invites(others.isEmpty() ? new ArrayList<>() : others.stream().map(InviteDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public static Delegation toEntity(DelegationDto dto) {
        if (dto == null) {
            return null;
        }
        Delegation delegation = new Delegation();
        delegation.setId(dto.getId());
        delegation.setNom(dto.getNom());
        delegation.setNombre(dto.getNombre());
        List<Invite> invites1 = new ArrayList<>(List.of(InviteDto.toEntity(dto.getChef())));
        if (!dto.getInvites().isEmpty()) {
            invites1.addAll(dto.getInvites().stream().map(InviteDto::toEntity).toList());
        }
        delegation.setInvites(invites1);
        return delegation;
    }
}
