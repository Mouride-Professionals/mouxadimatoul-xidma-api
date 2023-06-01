package com.touba.backend.service.impl;

import com.touba.backend.dto.ResidenceDto;
import com.touba.backend.dto.request.ResidenceRequest;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.model.Residence;
import com.touba.backend.repository.ResidenceRepository;
import com.touba.backend.repository.UtilisateurRepository;
import com.touba.backend.service.ResidenceService;
import com.touba.backend.service.RessourceService;
import com.touba.backend.validator.ResidenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidenceServiceImpl implements ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RessourceService ressourceService;

    @Override
    public ResidenceDto save(ResidenceRequest request) {
        List<String> errors = ResidenceValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("La résidence n'est pas valid", errors);
        }
        Residence residence = ResidenceRequest.toEntity(request);
        if (request.getImage() != null) {
            try {
                residence.setImage(ressourceService.uploadFile(request.getImage()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        residence.setResponsable(
                utilisateurRepository.findByUsername(request.getResponsable()).orElseThrow(() ->
                        new EntityNotFoundException("L'utilisateur n'existe pas")
                )
        );
        return ResidenceDto.fromEntity(residenceRepository.save(residence));
    }

    @Override
    public ResidenceDto update(ResidenceDto dto) {
        List<String> errors = ResidenceValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("La résidence n'est pas valid", errors);
        }
        return ResidenceDto.fromEntity(residenceRepository.save(ResidenceDto.toEntity(dto)));
    }

    @Override
    public List<ResidenceDto> findAll() {
        return residenceRepository.findAll().stream().map(ResidenceDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ResidenceDto findById(Long id) {
        return ResidenceDto.fromEntity(residenceRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("La résidence est introuvable")
        ));
    }

    @Override
    public ResidenceDto findByResponsable(String responsable) {
        return ResidenceDto.fromEntity(residenceRepository.findByResponsable(responsable).orElseThrow(() ->
            new EntityNotFoundException("La résidence est introuvable")
        ));
    }
}
