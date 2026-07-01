package com.touba.backend.controller;

import com.touba.backend.repository.RessourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.touba.backend.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/ressources")
@RequiredArgsConstructor
public class RessourceController {

    private final RessourceRepository ressourceRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable Long id) {
        return ressourceRepository.findById(id)
                .map(r -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, r.getType())
                        .body(r.getFichier()))
                .orElse(ResponseEntity.notFound().build());
    }
}
