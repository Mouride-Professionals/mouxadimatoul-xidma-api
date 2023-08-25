package com.touba.backend.dto.request;

import com.touba.backend.dto.AccueillantDto;
import com.touba.backend.dto.ChambreDto;
import com.touba.backend.dto.ResponsableDto;
import lombok.Data;

@Data
public class InviteRequest {

    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String email;
    private ChambreDto chambre;
    private AccueillantDto accueillant;
    private ResponsableDto responsable;
    private Boolean presence;

}
