package com.example.dszcrturest.ExcelService;

import com.example.dszcrturest.Model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ExcelController {
    @Autowired
    private ExcelFileReaderService excelFileReaderService;

    @GetMapping("/getExcelData")
    public ArrayList<ArrayList<HashMap<String, Day>>> getExcelData() throws IOException {
        ArrayList<ArrayList<HashMap<String, Day>>> data = excelFileReaderService.readExcelFile("/Users/denischernovs/Documents/GitHub/DSZC-Rest-API/src/main/java/com/example/dszcrturest/Schedule/DownloadedSchedule/dszc.dienas.xlsx");

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
