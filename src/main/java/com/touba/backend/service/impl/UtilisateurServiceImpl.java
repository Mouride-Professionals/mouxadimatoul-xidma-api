package com.touba.backend.service.impl;

import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.dto.UtilisateurDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
import com.touba.backend.model.AccountType;
import com.touba.backend.model.Utilisateur;
import com.touba.backend.model.Assignment;
import com.touba.backend.repository.AssignmentRepository;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Page<UtilisateurDto> findAll(int page, int size, String search, String accountType) {
        Pageable pageable = PageRequest.of(page, size);
        AccountType type = (accountType != null && !accountType.isBlank()) ? AccountType.valueOf(accountType) : null;
        Page<Utilisateur> utilisateurs = utilisateurRepository.findAllBySearch(pageable, search.toLowerCase().trim(), type);

        if (AccountType.KHIDMA_AGENT.equals(type)) {
            List<Long> agentIds = utilisateurs.getContent().stream()
                    .map(Utilisateur::getId)
                    .collect(Collectors.toList());
            Map<Long, Assignment> assignmentMap = assignmentRepository.findAllByAgentIdIn(agentIds).stream()
                    .collect(Collectors.toMap(a -> a.getAgent().getId(), a -> a, (a, b) -> a));
            return utilisateurs.map(u -> UtilisateurDto.fromEntityWithAssignment(u, assignmentMap.get(u.getId())));
        }

        return utilisateurs.map(UtilisateurDto::fromEntity);
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
        if (utilisateur.getAccountType() == null) {
            utilisateur.setAccountType(AccountType.KHIDMA_AGENT);
        }
        String rawPassword = (dto.getPassword() != null && !dto.getPassword().isBlank())
                ? dto.getPassword() : "test";
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(rawPassword));
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
    public void changeOwnPassword(String currentPassword, String newPassword) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(currentPassword, utilisateur.getPassword())) {
            throw new EntityInvalidException(ErrorCode.AUTH_BAD_CREDENTIALS, ErrorCode.VALIDATION_UTILISATEUR_INVALID);
        }
        utilisateur.setPassword(encoder.encode(newPassword));
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND));
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        utilisateurRepository.save(utilisateur);
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
