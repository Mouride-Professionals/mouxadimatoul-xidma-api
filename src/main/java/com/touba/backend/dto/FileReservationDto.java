package com.touba.backend.dto;

import com.touba.backend.model.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Builder
public class FileReservationDto {
    private String invite;
    private String delegation;
    private String residence;
    private String chambre;
    private String accueillant;
    private String dateEntree;
    private String dateSortie;
    private String presence;

    public static FileReservationDto mapToFile(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        return FileReservationDto.builder()
                .invite(reservation.getInvite().getPrenom() + " " + reservation.getInvite().getNom().toUpperCase())
                .delegation(reservation.getInvite().getDelegation() != null ? reservation.getInvite().getDelegation().getNom() : "")
                .residence(reservation.getChambre().getPavillon().getResidence().getLibelle())
                .chambre(reservation.getChambre().getReference())
                .accueillant(reservation.getAccueillant().getUtilisateur().getPrenom() + " " +reservation.getAccueillant().getUtilisateur().getNom())
                .dateEntree(dateFormatter.format(reservation.getDateEntree()))
                .dateSortie(dateFormatter.format(reservation.getDateSortie()))
                .presence(reservation.getPresence() ? "oui" : "non")
                .build();
    }
}
