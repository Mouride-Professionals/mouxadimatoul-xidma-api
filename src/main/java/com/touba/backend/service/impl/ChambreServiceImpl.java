package com.touba.backend.service.impl;

import com.touba.backend.dto.ChambreDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.model.Chambre;
import com.touba.backend.repository.ChambreRepository;
import com.touba.backend.service.ChambreService;
import com.touba.backend.validator.ChambreValidator;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChambreServiceImpl implements ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public ChambreDto save(ChambreDto dto) {
        List<String> errors = ChambreValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("La chambre est invalide", errors);
        }
        Chambre chambre = ChambreDto.toEntity(dto);
        chambre.setReference(UUID.randomUUID().toString().substring(0, 10));
        return ChambreDto.fromEntity(chambreRepository.save(chambre));
    }

    @Override
    public ChambreDto update(ChambreDto dto) {
        List<String> errors = ChambreValidator.validateUpdate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("La chambre est invalide", errors);
        }
        return ChambreDto.fromEntity(chambreRepository.save(ChambreDto.toEntity(dto)));
    }

    @Override
    public ChambreDto findById(Long id) {
        if (id == null) {
            throw new EntityInvalidException("L'id ne doit pas être nul");
        }
        return ChambreDto.fromEntity(
                chambreRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("La chambre n'existe pas")
                )
        );
    }

    @Override
    public Page<ChambreDto> findAllByPavillon(Long pavillon, int page, int size) {
        if (pavillon == null) {
            throw new EntityInvalidException("L'id du pavillon ne doit pas être nul");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("numero"));
        return chambreRepository.findAllByPavillon(pavillon, pageable).map(ChambreDto::fromEntity);
    }
}
