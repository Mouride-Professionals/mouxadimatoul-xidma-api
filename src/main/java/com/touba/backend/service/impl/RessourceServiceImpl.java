package com.touba.backend.service.impl;

import com.touba.backend.model.Ressource;
import com.touba.backend.service.RessourceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RessourceServiceImpl implements RessourceService {
    @Override
    public Ressource uploadFile(MultipartFile file) throws IOException {
        if (file == null) { return null; }
        Ressource ressource = new Ressource();
        ressource.setNom(file.getOriginalFilename());
        ressource.setType(file.getContentType());
        ressource.setFichier(file.getBytes());
        return ressource;
    }
}
