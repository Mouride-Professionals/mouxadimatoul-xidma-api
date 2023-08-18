package com.touba.backend.service.impl;

import com.touba.backend.dto.PavillonDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
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
        List<String> errors = PavillonValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("Le pavillon n'est pas valid", errors);
        }
        Pavillon pavillon =PavillonDto.toEntity(dto);
        pavillon.getChambres().forEach(c -> {
            c.setPavillon(pavillon);
            c.setReference(
                Arrays.stream(c.getPavillon().getLibelle().split(" "))
                        .map(word -> word.charAt(0))
                        .toString() + "-" + c.getNiveau().toString() + c.getNumero()
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
                pavillonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pavillon n'existe pas"))
        );
    }
}
