package com.touba.backend.dto;

import com.touba.backend.model.AccountType;
import com.touba.backend.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDto {

    private Long id;

    private String username;

    private AccountType accountType;

    private Boolean statut;

    private String prenom;

    private String nom;

    private String telephone;

    private String whatsapp;

    private String password;

    private Boolean hasAssignment;

    private String assignedResidenceName;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .username(utilisateur.getUsername())
                .accountType(utilisateur.getAccountType())
                .statut(utilisateur.getStatut())
                .prenom(utilisateur.getPrenom())
                .nom(utilisateur.getNom())
                .telephone(utilisateur.getTelephone())
                .whatsapp(utilisateur.getWhatsapp())
                .build();
    }

    public static UtilisateurDto fromEntityWithAssignment(Utilisateur utilisateur, com.touba.backend.model.Assignment assignment) {
        if (utilisateur == null) return null;
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .username(utilisateur.getUsername())
                .accountType(utilisateur.getAccountType())
                .statut(utilisateur.getStatut())
                .prenom(utilisateur.getPrenom())
                .nom(utilisateur.getNom())
                .telephone(utilisateur.getTelephone())
                .whatsapp(utilisateur.getWhatsapp())
                .hasAssignment(assignment != null)
                .assignedResidenceName(assignment != null ? assignment.getResidence().getLibelle() : null)
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto dto) {
        if (dto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setUsername(dto.getUsername());
        utilisateur.setAccountType(dto.getAccountType());
        utilisateur.setStatut(dto.getStatut());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setNom(dto.getNom());
        utilisateur.setTelephone(dto.getTelephone());
        utilisateur.setWhatsapp(dto.getWhatsapp());
        return utilisateur;
    }

}
