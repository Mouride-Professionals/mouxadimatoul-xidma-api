package com.touba.backend.dto.request;

import com.touba.backend.model.Residence;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class ResidenceRequest {

    private Long id;

    private String libelle;

    private String description;

    private String adresse;

    private String telephoneResidence;

    private MultipartFile image;

    private String responsable;

    public static Residence toEntity(ResidenceRequest request) {
        if (request == null) {
            return null;
        }
        Residence residence = new Residence();
        residence.setId(request.getId());
        residence.setLibelle(request.getLibelle());
        residence.setDescription(request.getDescription());
        residence.setAdresse(request.getAdresse());
        residence.setTelephoneResidence(request.getTelephoneResidence());
        return residence;
    }


}
