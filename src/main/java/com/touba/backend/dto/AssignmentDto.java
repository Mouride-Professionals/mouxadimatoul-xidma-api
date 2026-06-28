package com.touba.backend.dto;

import com.touba.backend.model.Assignment;
import com.touba.backend.model.Responsibility;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class AssignmentDto {

    private Long id;
    private UtilisateurDto agent;
    private ResidenceDto residence;
    private Set<Responsibility> responsibilities;
    private List<RotationSlotDto> rotationSlots;
    private LocalDate startDate;
    private LocalDate endDate;

    public static AssignmentDto fromEntity(Assignment assignment) {
        if (assignment == null) {
            return null;
        }
        return AssignmentDto.builder()
                .id(assignment.getId())
                .agent(UtilisateurDto.fromEntity(assignment.getAgent()))
                .residence(ResidenceDto.builder()
                        .id(assignment.getResidence().getId())
                        .libelle(assignment.getResidence().getLibelle())
                        .build())
                .responsibilities(assignment.getResponsibilities())
                .rotationSlots(assignment.getRotationSlots().stream()
                        .map(RotationSlotDto::fromEntity)
                        .collect(Collectors.toList()))
                .startDate(assignment.getStartDate())
                .endDate(assignment.getEndDate())
                .build();
    }
}
