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

public class UploadExcelFile {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<FileReservationDto> data;

    public UploadExcelFile(List<FileReservationDto> data) {
        this.data = data;
        this.workbook = new XSSFWorkbook();
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader("reservation", List.of(
                "Invité",
                "Délégation",
                "Résidence",
                "Chambre",
                "Accueillant",
                "Date d'enttrée",
                "Date de sortie",
                "Cérémonie officielle"
        ));
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeHeader(String sheetName, List<String> headers) {
        this.sheet = this.workbook.createSheet(sheetName);
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
            createCell(row, columnCount++, record.getResidence(), style);
            createCell(row, columnCount++, record.getChambre(), style);
            createCell(row, columnCount++, record.getAccueillant(), style);
            createCell(row, columnCount++, record.getDateEntree(), style);
            createCell(row, columnCount++, record.getDateSortie(), style);
            createCell(row, columnCount++, record.getPresence(), style);
        }
    }
}
