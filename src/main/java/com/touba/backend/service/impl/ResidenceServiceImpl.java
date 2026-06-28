package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.ResidenceDto;
import com.touba.backend.dto.request.ResidenceRequest;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.AccountType;
import com.touba.backend.model.Residence;
import com.touba.backend.model.Utilisateur;
import com.touba.backend.repository.ResidenceRepository;
import com.touba.backend.service.ResidenceService;
import com.touba.backend.service.RessourceService;
import com.touba.backend.validator.ResidenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidenceServiceImpl implements ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private RessourceService ressourceService;

    @Override
    public ResidenceDto save(ResidenceRequest request) {
        List<ValidationErrorDto> errors = ResidenceValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_RESIDENCE_INVALID, ErrorCode.VALIDATION_RESIDENCE_INVALID, errors);
        }
        Residence residence = ResidenceRequest.toEntity(request);
        if (request.getImage() != null) {
            try {
                residence.setImage(ressourceService.uploadFile(request.getImage()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Utilisateur responsable = new Utilisateur();
        responsable.setAccountType(AccountType.KHIDMA_AGENT);
        responsable.setPrenom(request.getPrenom());
        responsable.setNom(request.getNom());
        responsable.setTelephone(request.getTelephone());
        responsable.setUsername(request.getTelephone());
        responsable.setStatut(true);
        responsable.setPassword(new BCryptPasswordEncoder().encode("test"));
        residence.setResponsable(responsable);
        return ResidenceDto.fromEntity(residenceRepository.save(residence));
    }

    @Override
    public ResidenceDto update(ResidenceDto dto) {
        List<ValidationErrorDto> errors = ResidenceValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_RESIDENCE_INVALID, ErrorCode.VALIDATION_RESIDENCE_INVALID, errors);
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
            new EntityNotFoundException(ErrorCode.RESIDENCE_NOT_FOUND, ErrorCode.RESIDENCE_NOT_FOUND)
        ));
    }

    @Override
    public ResidenceDto findByResponsable(String responsable) {
        return ResidenceDto.fromEntity(residenceRepository.findByResponsable(responsable).orElseThrow(() ->
            new EntityNotFoundException(ErrorCode.RESIDENCE_NOT_FOUND, ErrorCode.RESIDENCE_NOT_FOUND)
        ));
    }
}
