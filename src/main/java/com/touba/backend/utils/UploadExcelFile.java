package com.touba.backend.utils;

import com.touba.backend.dto.FileReservationDto;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UploadExcelFile {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<FileReservationDto> data;
    private final String locale;

    public UploadExcelFile(List<FileReservationDto> data) {
        this(data, "fr");
    }

    public UploadExcelFile(List<FileReservationDto> data, String locale) {
        this.data = data;
        this.locale = normalizeLocale(locale);
        this.workbook = new XSSFWorkbook();
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader(sheetName(), headers());
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeHeader(String sheetName, List<String> headers) {
        this.sheet = this.workbook.createSheet(sheetName);
        this.sheet.setRightToLeft(isArabic());
        Row row = this.sheet.createRow(0);
        CellStyle cellStyle = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);
        for (int i = 0; i < headers.size(); i++) {
            createCell(row, i, headers.get(i), cellStyle);
        }
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Boolean) {
            cell.setCellValue((Boolean) valueOfCell);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (FileReservationDto record: data) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getInvite(), style);
            createCell(row, columnCount++, record.getDelegation(), style);
            createCell(row, columnCount++, record.getNombre(), style);
            createCell(row, columnCount++, record.getResidence(), style);
            createCell(row, columnCount++, record.getChambre(), style);
            createCell(row, columnCount++, record.getAccueillant(), style);
            createCell(row, columnCount++, record.getDateEntree(), style);
            createCell(row, columnCount++, record.getDateSortie(), style);
            createCell(row, columnCount++, record.getPresence(), style);
        }
    }

    private List<String> headers() {
        if (isArabic()) {
            return List.of(
                    "الضيف",
                    "الوفد",
                    "عدد الأشخاص",
                    "الإقامة",
                    "الغرفة",
                    "المستقبل",
                    "تاريخ الدخول",
                    "تاريخ الخروج",
                    "حضور الحفل الرسمي"
            );
        }

        return List.of(
                "Invité",
                "Délégation",
                "Nbr personnes",
                "Résidence",
                "Chambre",
                "Accueillant",
                "Date d'entrée",
                "Date de sortie",
                "Cérémonie officielle"
        );
    }

    private String sheetName() {
        return isArabic() ? "الحجوزات" : "reservations";
    }

    private boolean isArabic() {
        return "ar".equals(this.locale);
    }

    private String normalizeLocale(String locale) {
        return locale != null && locale.toLowerCase(Locale.ROOT).startsWith("ar") ? "ar" : "fr";
    }
}
