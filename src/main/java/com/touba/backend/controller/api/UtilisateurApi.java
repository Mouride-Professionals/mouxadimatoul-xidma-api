package com.touba.backend.controller.api;

import com.touba.backend.dto.UtilisateurDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @GetMapping
    Page<UtilisateurDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String accountType);

    @PostMapping
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @PutMapping
    UtilisateurDto update(@RequestBody UtilisateurDto dto);

    @GetMapping("/info")
    UtilisateurDto getAccount();

    @GetMapping("/{id}")
    UtilisateurDto findById(@PathVariable Long id);

    @PutMapping("/statut/{id}")
    UtilisateurDto changeStatut(@PathVariable Long id);

}
