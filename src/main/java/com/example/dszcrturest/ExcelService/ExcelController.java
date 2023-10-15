package com.example.dszcrturest.ExcelService;

import com.example.dszcrturest.Model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {
    @Autowired
    private ExcelFileReaderService excelFileReaderService;

    @GetMapping("/getExcelData")
    public List<String[]> getExcelData() throws IOException {
        List<String[]> data = excelFileReaderService.readExcelFile("/Users/denischernovs/Documents/GitHub/DSZC-Rest-API/src/main/java/com/example/dszcrturest/Schedule/DownloadedSchedule/dszc.dienas.xlsx");

        //printExcelData(data);
        return data;
    }

    private static void printExcelData(List<String[]> data) {
        for (String[] rowData : data) {
            for (String cellValue : rowData) {
                System.out.print(cellValue + " ");
            }
            System.out.println();
        }
    }
}
