package com.touba.backend.dto;

import com.touba.backend.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDto {

    private Long id;

    private String username;

    private RoleDto role;

    private Boolean statut;

    private String prenom;

    private String nom;

    private String telephone;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .username(utilisateur.getUsername())
                .role(RoleDto.fromEntity(utilisateur.getRole()))
                .statut(utilisateur.getStatut())
                .prenom(utilisateur.getPrenom())
                .nom(utilisateur.getNom())
                .telephone(utilisateur.getTelephone())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto dto) {
        if (dto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setUsername(dto.getUsername());
        utilisateur.setRole(RoleDto.toEntity(dto.getRole()));
        utilisateur.setStatut(dto.getStatut());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setNom(dto.getNom());
        utilisateur.setTelephone(dto.getTelephone());
        return utilisateur;
    }

}
