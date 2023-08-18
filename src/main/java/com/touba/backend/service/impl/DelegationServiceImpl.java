package com.touba.backend.service.impl;

import com.touba.backend.dto.DelegationDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
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
        List<String> errors = DelegationValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("La délégation est invalide", errors);
        }
        Delegation delegation = DelegationDto.toEntity(dto);
        delegation.getInvites().forEach(invite -> invite.setDelegation(delegation));
        return DelegationDto.fromEntity(delegationRepository.save(delegation));
    }

    @Override
    public DelegationDto findById(Long id) {
        return DelegationDto.fromEntity(
                delegationRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Pas de délégation avec cet ID")
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
