package com.touba.backend.service;

import com.touba.backend.model.Ressource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RessourceService {

    Ressource uploadFile(MultipartFile file) throws IOException;

}
