package com.touba.backend.service.impl;

import com.touba.backend.dto.AccueillantDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.repository.AccueillantRepository;
import com.touba.backend.service.AccueillantService;
import com.touba.backend.validator.AccueillantValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccueillantServiceImpl implements AccueillantService {

    private final AccueillantRepository accueillantRepository;

    @Override
    public AccueillantDto save(AccueillantDto dto) {
        List<String> errors = AccueillantValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("L'accueillant est invalid", errors);
        }
        return AccueillantDto.fromEntity(
                accueillantRepository.save(AccueillantDto.toEntity(dto))
        );
    }

    @Override
    public AccueillantDto update(AccueillantDto dto) {
        List<String> errors = AccueillantValidator.validate(dto);
        if (dto.getId() == null) {
            errors.add("L'ID est obligatoire pour modifier l'accueillant");
        }
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("L'accueillant est invalid", errors);
        }
        return AccueillantDto.fromEntity(
                accueillantRepository.save(AccueillantDto.toEntity(dto))
        );
    }

    @Override
    public AccueillantDto findById(Long id) {
        return AccueillantDto.fromEntity(
                accueillantRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Pas d'accueillant avec cet ID")
                )
        );
    }

    @Override
    public Page<AccueillantDto> findAllByParams(int page, int size, Long idRes, String search) {
        Pageable pageable = PageRequest.of(page, size);
        return accueillantRepository.findAllByParams(pageable, idRes, search).map(AccueillantDto::fromEntity);
    }

    @Override
    public void delete(Long id) {

    }
}
