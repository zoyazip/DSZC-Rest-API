package com.example.dszcrturest.ExcelService;

import com.example.dszcrturest.Model.Schedule;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Component
public class ExcelFileReaderService {
    private ArrayList<String[]> rawData = new ArrayList<>();
    private ArrayList<Schedule> data = new ArrayList<>();
    private String[] specialisations = {"Datorsistēmas, Informācijas tehnoloģija, Automātika un datortehnika",  "Viedā elektroenerģētika", "Automobiļu transports",  "Inženiertehnika, mehānika un mašīnbūve", "Mehatronika", "Būvniecība", };
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
        //List<String[]> data = new ArrayList<>();
        int startRow = 4;
        Iterator<Row> rowiterator = sheet.iterator();
        while (rowiterator.hasNext()) {
            Row row = rowiterator.next();

            // Skip rows until we reach the start row.
            if (row.getRowNum() < startRow) {
                continue;
            }

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
                if (rowData[i] == "") {
                    rowData[i] = "FREE";
                }
                i++;
            }
            this.rawData.add(rowData);
        }

        workbook.close();
        createContent(rawData);
        return rawData;
    }


    private void createContent(List<String[]> data) {

        Map<String, List<Schedule.Day>> schedule = new HashMap<>();

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String course = "";
        ArrayList<String> subjectRaw = new ArrayList<>();
        String time = "";
        ArrayList<Schedule.Lesson> lessons = new ArrayList<>();

        ArrayList<Schedule.Day> days = new ArrayList<>();
        ArrayList<String[]> copyOfRawData = new ArrayList<>(data);

        for (String day : daysOfWeek) {
            List<Schedule.Day> daySchedule = new ArrayList<>();
            for (int i = 0; i < copyOfRawData.size(); i++) {
                for (int j = 0; j < copyOfRawData.get(i).length; j++) {
                    //System.out.println(i + " - " + j + ") " + copyOfRawData.get(i)[j]);
                    if (j == 1) {
                        course = copyOfRawData.get(i)[j];
                    }
                    else if (j == 2) {
                        time = copyOfRawData.get(i)[j];
                    }
                    else if (copyOfRawData.get(i)[j] != "FREE" && copyOfRawData.get(i)[j] != null && j != 1 && j != 2 && j != 0 && j != 1 && j != 2) {
                        Schedule.Lesson lesson = new Schedule.Lesson(course, copyOfRawData.get(i)[j], "", "", "", time);
                        daySchedule.add(new Schedule.Day(lesson));
//                        subjectRaw.add(copyOfRawData.get(i)[j]);
//                        lessons.add(new Schedule.Lesson(course, copyOfRawData.get(i)[j], "", "", "", time));
                    }
                }
            }

        }



//        for (int i = 0; i < copyOfRawData.size(); i++) {
//
//            for (int j = 0; j < copyOfRawData.get(i).length; j++) {
//                //System.out.println(i + " - " + j + ") " + copyOfRawData.get(i)[j]);
//                if (j == 1) {
//                    course = copyOfRawData.get(i)[j];
//                }
//                else if (j == 2) {
//                    time = copyOfRawData.get(i)[j];
//                }
//                else if (copyOfRawData.get(i)[j] != "FREE" && copyOfRawData.get(i)[j] != null && j != 1 && j != 2 && j != 0 && j != 1 && j != 2) {
//                    subjectRaw.add(copyOfRawData.get(i)[j]);
//                    lessons.add(new Schedule.Lesson(course, copyOfRawData.get(i)[j], "", "", "", time));
//                }
//            }
//
//        }

//        for (String subject : subjectRaw) {
//            lessons.add(new Schedule.Lesson(course, subject, "", "", "", time));
//        }

//        for (String day : daysOfWeek) {
//            days.add(new Schedule.Day((Map<String, List<Schedule.Lesson>>) lessons));
//        }

        int c = 0;
        for (Schedule.Lesson lesson : lessons) {
            System.out.println(c + ") " + lesson.getSubject());
            c++;
        }
    }
}

/*
    Need to create 6 arrays for each day of the week.
    Fill them with schedule for each day



 */