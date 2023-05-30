package com.touba.backend.service;

import com.touba.backend.dto.UtilisateurDto;
import org.springframework.data.domain.Page;

public interface UtilisateurService {

    Page<UtilisateurDto> findAll(int page, int size, String search, String role);

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto getAccount();

    UtilisateurDto update(UtilisateurDto dto);

    UtilisateurDto findById(Long id);

    UtilisateurDto changeStatut(Long id);

}
