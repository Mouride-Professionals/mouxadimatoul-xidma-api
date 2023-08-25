package com.touba.backend.service.impl;

import com.touba.backend.dto.UtilisateurDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.model.Role;
import com.touba.backend.model.Utilisateur;
import com.touba.backend.repository.RoleRepository;
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

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<UtilisateurDto> findAll(int page, int size, String search, String role) {
        Pageable pageable = PageRequest.of(page, size);
        return utilisateurRepository.findAllBySearch(pageable, search.toLowerCase().trim(), role).map(UtilisateurDto::fromEntity);
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new EntityInvalidException("L'utilisateur est invalid", errors);
        }
        Role role = roleRepository.findByLibelle("admin").orElseThrow(() -> new EntityNotFoundException("Role admin introuvable"));
        Utilisateur utilisateur = UtilisateurDto.toEntity(dto);
        utilisateur.setUsername(utilisateur.getTelephone());
        utilisateur.setStatut(true);
        utilisateur.setRole(role);
        utilisateur.setPassword(new BCryptPasswordEncoder().encode("test"));
        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UtilisateurDto getAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.hasLength(username)) {
            throw new EntityNotFoundException("L'utilisateur ne s'est pas connecté");
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Cet utilisateur n'existe pas"))
        );
    }

    @Override
    public UtilisateurDto update(UtilisateurDto dto) {
        return null;
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null) {
            throw new EntityInvalidException("L'id ne doit pas être null");
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("L'utilisateur n'existe pas"))
        );
    }

    @Override
    public UtilisateurDto changeStatut(Long id) {
        if (id == null) {
            throw new EntityInvalidException("L'id ne doit pas être null");
        }
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("L'utilisateur n'existe pas"));
        utilisateur.setStatut(!utilisateur.getStatut());
        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }
}
