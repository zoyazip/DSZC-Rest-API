package com.example.dszcrturest.ExcelService;

import com.example.dszcrturest.Model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/*
    ExcelController is a RestController of Spring, which controls Excel Reading process
 */

@RestController
public class ExcelController {
    @Autowired
    private ExcelFileService excelFileService;

    private LinkedHashMap<String, Day> data;
    @GetMapping("/schedule")
    public LinkedHashMap<String, Day> schedule() throws IOException {
        createSchedule();
        return data;
    }

    @GetMapping("/schedule/{day}")
    public Day getDaySchedule(@PathVariable String day) throws IOException {
        createSchedule();

        return data.get(day);
    }

    private void createSchedule() throws IOException {
        excelFileService.readExcelFile();
        data = excelFileService.createContent();
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
