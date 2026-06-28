package com.touba.backend.controller;

import com.touba.backend.controller.api.UtilisateurApi;
import com.touba.backend.dto.UtilisateurDto;
import com.touba.backend.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController implements UtilisateurApi {

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public Page<UtilisateurDto> findAll(int page, int size, String search, String accountType) {
        return utilisateurService.findAll(page, size, search, accountType);
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto update(UtilisateurDto dto) {
        return utilisateurService.update(dto);
    }

    @Override
    public UtilisateurDto getAccount() {
        return utilisateurService.getAccount();
    }

    @Override
    public UtilisateurDto findById(Long id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto changeStatut(Long id) {
        return utilisateurService.changeStatut(id);
    }
}
