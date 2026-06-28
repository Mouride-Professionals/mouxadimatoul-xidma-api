package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.AssignmentDto;
import com.touba.backend.dto.RotationSlotDto;
import com.touba.backend.dto.request.AssignmentRequest;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.Assignment;
import com.touba.backend.model.Residence;
import com.touba.backend.model.RotationSlot;
import com.touba.backend.model.Utilisateur;
import com.touba.backend.repository.AssignmentRepository;
import com.touba.backend.repository.ResidenceRepository;
import com.touba.backend.repository.UtilisateurRepository;
import com.touba.backend.service.AssignmentService;
import com.touba.backend.validator.AssignmentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ResidenceRepository residenceRepository;

    @Override
    public AssignmentDto save(AssignmentRequest request) {
        List<ValidationErrorDto> errors = AssignmentValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_ASSIGNMENT_INVALID, ErrorCode.VALIDATION_ASSIGNMENT_INVALID, errors);
        }

        Utilisateur agent = utilisateurRepository.findById(request.getAgentId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND));

        Residence residence = residenceRepository.findById(request.getResidenceId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.RESIDENCE_NOT_FOUND, ErrorCode.RESIDENCE_NOT_FOUND));

        Assignment assignment = new Assignment();
        assignment.setAgent(agent);
        assignment.setResidence(residence);
        assignment.setResponsibilities(request.getResponsibilities());
        assignment.setStartDate(request.getStartDate());
        assignment.setEndDate(request.getEndDate());

        if (request.getRotationSlots() != null) {
            List<RotationSlot> slots = request.getRotationSlots().stream()
                    .map(dto -> {
                        RotationSlot slot = RotationSlotDto.toEntity(dto);
                        slot.setAssignment(assignment);
                        return slot;
                    })
                    .collect(Collectors.toList());
            assignment.setRotationSlots(slots);
        }

        return AssignmentDto.fromEntity(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDto update(Long id, AssignmentRequest request) {
        List<ValidationErrorDto> errors = AssignmentValidator.validate(request);
        if (id == null) {
            errors.add(ValidationErrorDto.of("id", ErrorCode.ASSIGNMENT_ID_REQUIRED));
        }
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_ASSIGNMENT_INVALID, ErrorCode.VALIDATION_ASSIGNMENT_INVALID, errors);
        }

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ASSIGNMENT_NOT_FOUND, ErrorCode.ASSIGNMENT_NOT_FOUND));

        Utilisateur agent = utilisateurRepository.findById(request.getAgentId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND));

        Residence residence = residenceRepository.findById(request.getResidenceId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.RESIDENCE_NOT_FOUND, ErrorCode.RESIDENCE_NOT_FOUND));

        assignment.setAgent(agent);
        assignment.setResidence(residence);
        assignment.setResponsibilities(request.getResponsibilities());
        assignment.setStartDate(request.getStartDate());
        assignment.setEndDate(request.getEndDate());

        assignment.getRotationSlots().clear();
        if (request.getRotationSlots() != null) {
            List<RotationSlot> slots = request.getRotationSlots().stream()
                    .map(dto -> {
                        RotationSlot slot = RotationSlotDto.toEntity(dto);
                        slot.setAssignment(assignment);
                        return slot;
                    })
                    .collect(Collectors.toList());
            assignment.getRotationSlots().addAll(slots);
        }

        return AssignmentDto.fromEntity(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDto findById(Long id) {
        return AssignmentDto.fromEntity(
                assignmentRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ASSIGNMENT_NOT_FOUND, ErrorCode.ASSIGNMENT_NOT_FOUND))
        );
    }

    @Override
    public Page<AssignmentDto> findAllByResidence(Long residenceId, int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (search != null && !search.isBlank()) {
            return assignmentRepository.findAllByResidenceIdAndSearch(pageable, residenceId, search.toLowerCase().trim())
                    .map(AssignmentDto::fromEntity);
        }
        return assignmentRepository.findAllByResidenceId(residenceId, pageable)
                .map(AssignmentDto::fromEntity);
    }

    @Override
    public List<AssignmentDto> findAllByAgent(Long agentId) {
        return assignmentRepository.findAllByAgentId(agentId).stream()
                .map(AssignmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ASSIGNMENT_NOT_FOUND, ErrorCode.ASSIGNMENT_NOT_FOUND));
        assignmentRepository.delete(assignment);
    }
}
