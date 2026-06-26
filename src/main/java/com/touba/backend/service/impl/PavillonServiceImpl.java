package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.PavillonDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.Pavillon;
import com.touba.backend.repository.PavillonRepository;
import com.touba.backend.service.PavillonService;
import com.touba.backend.validator.PavillonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PavillonServiceImpl implements PavillonService {

    @Autowired
    private PavillonRepository pavillonRepository;

    @Override
    public PavillonDto save(PavillonDto dto) {
        List<ValidationErrorDto> errors = PavillonValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_PAVILLON_INVALID, ErrorCode.VALIDATION_PAVILLON_INVALID, errors);
        }
        Pavillon pavillon =PavillonDto.toEntity(dto);
        pavillon.getChambres().forEach(c -> {
            if (c.getNiveau() == null) {
                c.setNiveau(0);
            }
            c.setPavillon(pavillon);
            c.setReference(
                Arrays.stream(c.getPavillon().getLibelle().trim().split("\\s+"))
                        .map(word -> word.charAt(0))
                        .map(String::valueOf)
                        .collect(Collectors.joining())
                        .toUpperCase() + "-" + c.getNiveau().toString() + c.getNumero()
            );
        });
        return PavillonDto.fromEntity(pavillonRepository.save(pavillon));
    }

    @Override
    public List<PavillonDto> findAll() {
        return pavillonRepository.findAll().stream().map(PavillonDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<PavillonDto> findAllByResidence(Long id) {
        return pavillonRepository.findAllByResidence(id).stream().map(PavillonDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public PavillonDto findById(Long id) {
        return PavillonDto.fromEntity(
                pavillonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAVILLON_NOT_FOUND, ErrorCode.PAVILLON_NOT_FOUND))
        );
    }
}
