package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.DelegationDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.Delegation;
import com.touba.backend.repository.DelegationRepository;
import com.touba.backend.service.DelegationService;
import com.touba.backend.validator.DelegationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DelegationServiceImpl implements DelegationService {

    private final DelegationRepository delegationRepository;

    @Override
    public DelegationDto save(DelegationDto dto) {
        List<ValidationErrorDto> errors = DelegationValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_DELEGATION_INVALID, ErrorCode.VALIDATION_DELEGATION_INVALID, errors);
        }
        Delegation delegation = DelegationDto.toEntity(dto);
        delegation.getInvites().forEach(invite -> invite.setDelegation(delegation));
        return DelegationDto.fromEntity(delegationRepository.save(delegation));
    }

    @Override
    public DelegationDto update(DelegationDto dto) {
        List<ValidationErrorDto> errors = DelegationValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_DELEGATION_INVALID, ErrorCode.VALIDATION_DELEGATION_INVALID, errors);
        }
        Delegation delegation = delegationRepository.findById(dto.getId()).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.DELEGATION_NOT_FOUND, ErrorCode.DELEGATION_NOT_FOUND)
        );
        delegation.setNom(dto.getNom());
        delegation.setNombre(dto.getNombre());
        return DelegationDto.fromEntity(delegationRepository.save(delegation));
    }

    @Override
    public DelegationDto findById(Long id) {
        return DelegationDto.fromEntity(
                delegationRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.DELEGATION_NOT_FOUND, ErrorCode.DELEGATION_NOT_FOUND)
                )
        );
    }

    @Override
    public Page<DelegationDto> findAll(int page, int size) {
        return delegationRepository.findAll(PageRequest.of(page, size)).map(DelegationDto::fromEntity);
    }

    @Override
    public void delete(Long id) {
        delegationRepository.deleteById(id);
    }
}
