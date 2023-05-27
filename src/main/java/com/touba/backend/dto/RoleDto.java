package com.touba.backend.dto;

import com.touba.backend.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Long id;

    private String libelle;

    public static RoleDto fromEntity(Role role) {
        if (role == null) {
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .libelle(role.getLibelle())
                .build();
    }

    public static Role toEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setLibelle(dto.getLibelle());
        return role;
    }

}
