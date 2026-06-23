package com.touba.backend.dto;

import com.touba.backend.model.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Locale;

@Getter
@Setter
@Builder
public class FileReservationDto {
    private String invite;
    private String delegation;
    private Integer nombre;
    private String residence;
    private String chambre;
    private String accueillant;
    private String dateEntree;
    private String dateSortie;
    private String presence;

    public static FileReservationDto mapToFile(Reservation reservation) {
        return mapToFile(reservation, "fr");
    }

    public static FileReservationDto mapToFile(Reservation reservation, String locale) {
        if (reservation == null) {
            return null;
        }
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, toJavaLocale(locale));
        return FileReservationDto.builder()
                .invite(reservation.getInvite().getPrenom() + " " + reservation.getInvite().getNom().toUpperCase())
                .delegation(reservation.getInvite().getDelegation() != null ? reservation.getInvite().getDelegation().getNom() : "")
                .nombre(reservation.getInvite().getDelegation() != null ? reservation.getInvite().getDelegation().getNombre() : 0)
                .residence(reservation.getChambre().getPavillon().getResidence().getLibelle())
                .chambre(reservation.getChambre().getReference())
                .accueillant(reservation.getAccueillant().getUtilisateur().getPrenom() + " " +reservation.getAccueillant().getUtilisateur().getNom())
                .dateEntree(dateFormatter.format(reservation.getDateEntree()))
                .dateSortie(dateFormatter.format(reservation.getDateSortie()))
                .presence(formatPresence(reservation.getPresence(), locale))
                .build();
    }

    private static String formatPresence(Boolean presence, String locale) {
        boolean isPresent = Boolean.TRUE.equals(presence);
        if (isArabic(locale)) {
            return isPresent ? "نعم" : "لا";
        }
        return isPresent ? "Oui" : "Non";
    }

    private static Locale toJavaLocale(String locale) {
        return isArabic(locale) ? Locale.forLanguageTag("ar") : Locale.FRANCE;
    }

    private static boolean isArabic(String locale) {
        return locale != null && locale.toLowerCase(Locale.ROOT).startsWith("ar");
    }
}
