package com.touba.backend.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.touba.backend.dto.FileReservationDto;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ExportPdfFile {

    private final List<FileReservationDto> data;
    private final String locale;

    public ExportPdfFile(List<FileReservationDto> data, String locale) {
        this.data = data;
        this.locale = normalizeLocale(locale);
    }

    public void generatePdfFile(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.DARK_GRAY);
        Paragraph title = new Paragraph(isArabic() ? "قائمة الحجوزات" : "Liste des réservations", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(16f);
        document.add(title);

        PdfPTable table = new PdfPTable(headers().size());
        table.setWidthPercentage(100);
        table.setRunDirection(isArabic() ? PdfWriter.RUN_DIRECTION_RTL : PdfWriter.RUN_DIRECTION_LTR);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);
        for (String header : headers()) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(new Color(34, 139, 34));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(6f);
            table.addCell(cell);
        }

        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Color.BLACK);
        boolean alternate = false;
        for (FileReservationDto row : data) {
            Color bg = alternate ? new Color(240, 240, 240) : Color.WHITE;
            addCell(table, row.getInvite(), dataFont, bg);
            addCell(table, row.getDelegation(), dataFont, bg);
            addCell(table, String.valueOf(row.getNombre()), dataFont, bg);
            addCell(table, row.getResidence(), dataFont, bg);
            addCell(table, row.getChambre(), dataFont, bg);
            addCell(table, row.getAccueillant(), dataFont, bg);
            addCell(table, row.getDateEntree(), dataFont, bg);
            addCell(table, row.getDateSortie(), dataFont, bg);
            addCell(table, row.getPresence(), dataFont, bg);
            alternate = !alternate;
        }

        document.add(table);
        document.close();
    }

    private void addCell(PdfPTable table, String value, Font font, Color bg) {
        PdfPCell cell = new PdfPCell(new Phrase(value != null ? value : "", font));
        cell.setBackgroundColor(bg);
        cell.setPadding(4f);
        cell.setHorizontalAlignment(isArabic() ? Element.ALIGN_RIGHT : Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    private List<String> headers() {
        if (isArabic()) {
            return List.of("الضيف", "الوفد", "عدد الأشخاص", "الإقامة", "الغرفة", "المستقبل", "تاريخ الدخول", "تاريخ الخروج", "حضور الحفل الرسمي");
        }
        return List.of("Invité", "Délégation", "Nbr personnes", "Résidence", "Chambre", "Accueillant", "Date d'entrée", "Date de sortie", "Cérémonie officielle");
    }

    private boolean isArabic() {
        return "ar".equals(this.locale);
    }

    private String normalizeLocale(String locale) {
        return locale != null && locale.toLowerCase(Locale.ROOT).startsWith("ar") ? "ar" : "fr";
    }
}
