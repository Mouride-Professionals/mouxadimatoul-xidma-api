package com.touba.backend.dto.request;

import com.touba.backend.dto.RotationSlotDto;
import com.touba.backend.model.Responsibility;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class AssignmentRequest {

    private Long agentId;
    private Long residenceId;
    private Set<Responsibility> responsibilities;
    private List<RotationSlotDto> rotationSlots;
    private LocalDate startDate;
    private LocalDate endDate;
}
