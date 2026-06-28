package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.UtilisateurDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.AccountType;
import com.touba.backend.model.Utilisateur;
import com.touba.backend.repository.UtilisateurRepository;
import com.touba.backend.service.UtilisateurService;
import com.touba.backend.validator.UtilisateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Page<UtilisateurDto> findAll(int page, int size, String search, String accountType) {
        Pageable pageable = PageRequest.of(page, size);
        AccountType type = (accountType != null && !accountType.isBlank()) ? AccountType.valueOf(accountType) : null;
        return utilisateurRepository.findAllBySearch(pageable, search.toLowerCase().trim(), type).map(UtilisateurDto::fromEntity);
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<ValidationErrorDto> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException(ErrorCode.VALIDATION_UTILISATEUR_INVALID, ErrorCode.VALIDATION_UTILISATEUR_INVALID, errors);
        }
        Utilisateur utilisateur = UtilisateurDto.toEntity(dto);
        utilisateur.setUsername(utilisateur.getTelephone());
        utilisateur.setStatut(true);
        utilisateur.setAccountType(AccountType.KHIDMA_AGENT);
        utilisateur.setPassword(new BCryptPasswordEncoder().encode("test"));
        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UtilisateurDto getAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.hasLength(username)) {
            throw new EntityNotFoundException(ErrorCode.USER_NOT_AUTHENTICATED, ErrorCode.USER_NOT_AUTHENTICATED);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND))
        );
    }

    @Override
    public UtilisateurDto update(UtilisateurDto dto) {
        return null;
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null) {
            throw new EntityInvalidException(ErrorCode.USER_ID_REQUIRED, ErrorCode.VALIDATION_UTILISATEUR_INVALID);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND))
        );
    }

    @Override
    public UtilisateurDto changeStatut(Long id) {
        if (id == null) {
            throw new EntityInvalidException(ErrorCode.USER_ID_REQUIRED, ErrorCode.VALIDATION_UTILISATEUR_INVALID);
        }
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND));
        utilisateur.setStatut(!utilisateur.getStatut());
        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }
}
