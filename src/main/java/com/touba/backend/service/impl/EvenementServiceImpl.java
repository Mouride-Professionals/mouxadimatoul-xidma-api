package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.EvenementDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.repository.EvenementRepository;
import com.touba.backend.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public EvenementDto save(EvenementDto dto) {
        if (!StringUtils.hasLength(dto.getLibelle())) {
            throw new EntityInvalidException(
                    ErrorCode.VALIDATION_EVENEMENT_INVALID,
                    ErrorCode.VALIDATION_EVENEMENT_INVALID,
                    List.of(ValidationErrorDto.of("libelle", ErrorCode.EVENEMENT_NAME_REQUIRED))
            );
        }
        return EvenementDto.fromEntity(
                evenementRepository.save(EvenementDto.toEntity(dto))
        );
    }

    @Override
    public List<EvenementDto> findAll() {
        return evenementRepository.findAll().stream().map(EvenementDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public EvenementDto findById(Long id) {
        return EvenementDto.fromEntity(
                evenementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.EVENEMENT_NOT_FOUND, ErrorCode.EVENEMENT_NOT_FOUND))
        );
    }

    @Override
    public void delete(Long id) {
        evenementRepository.deleteById(id);
    }
}
