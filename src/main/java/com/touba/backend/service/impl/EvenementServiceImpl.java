package com.touba.backend.service.impl;

import com.touba.backend.dto.EvenementDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.repository.EvenementRepository;
import com.touba.backend.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public EvenementDto save(EvenementDto dto) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasLength(dto.getLibelle())) {
            throw new EntityInvalidException("L'événement est invalid", List.of("Le libelle est obligatoire"));
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
                evenementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("L'événement est introuvable"))
        );
    }

    @Override
    public void delete(Long id) {
        evenementRepository.deleteById(id);
    }
}
