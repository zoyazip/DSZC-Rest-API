package com.example.dszcrturest.ExcelService;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelFileReaderService {
    public List<String[]> readExcelFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(fis);
        } catch (IOException e) {
            throw e;
        } finally {
            fis.close();
        }

        if (workbook == null) {
            throw new IOException("Failed to open Excel file: " + filePath);
        }

        Sheet sheet = workbook.getSheetAt(0);

        // Iterate over the rows in the sheet and add the data to a list.
        List<String[]> data = new ArrayList<>();

        Iterator<Row> rowiterator = sheet.iterator();
        while (rowiterator.hasNext()) {
            Row row = rowiterator.next();
            Iterator<Cell> cellIterator = row.iterator();

            String[] rowData = new String[row.getLastCellNum()];
            int i = 0;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                // Convert numeric cell values to string values.
                if (cell.getCellType() == CellType.NUMERIC) {
                    rowData[i] = cell.getNumericCellValue() + "";
                } else {
                    rowData[i] = cell.getStringCellValue();
                }

                i++;
            }
            data.add(rowData);
        }

        workbook.close();

        return data;
    }
}
