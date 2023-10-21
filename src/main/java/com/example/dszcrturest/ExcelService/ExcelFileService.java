package com.example.dszcrturest.ExcelService;

import com.example.dszcrturest.Model.Day;
import com.example.dszcrturest.Model.Lesson;
import com.example.dszcrturest.Schedule.ScheduleController;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/*
    ExcelFileReaderService is a Spring Service which contains business logic for this application.
    It's processing and manipulating updated excel file data, contains helping private methods
 */

@Service
public class ExcelFileService {
    private ArrayList<String[]> data = new ArrayList<>();
    @Autowired
    private ScheduleController sc;
    private String filePath = System.getProperty("user.dir") + "/src/main/java/com/example/dszcrturest/Schedule/DownloadedSchedule/Schedule.xlsx";
    public void readExcelFile() throws IOException {
        this.sc.DownloadFile();
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
                if (rowData[i] == "" || rowData[i].isEmpty() || rowData[i] == null) {
                    rowData[i] = "FREE";
                }
                i++;
            }
            this.data.add(rowData);
        }

        workbook.close();
    }
    public LinkedHashMap<String, Day> createContent() {

        List<String> days = new ArrayList<>();
        String lessonName = "";
        String lessonTime = "";
        String lessonInstructor = "";
        String lessonRoom = "";
        String lessonCourse = "";
        String lessonDay = "";

        ArrayList<Lesson> mondaySchedule = new ArrayList<>();
        ArrayList<Lesson> tuesdaySchedule = new ArrayList<>();
        ArrayList<Lesson> wednesdaySchedule = new ArrayList<>();
        ArrayList<Lesson> thursdaySchedule = new ArrayList<>();
        ArrayList<Lesson> fridaySchedule = new ArrayList<>();
        ArrayList<Lesson> saturdaySchedule = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {

                if (i % 6 == 0 && j == 0) {
                    days.add(data.get(i)[j]);
                }
                if (j == 2) {
                    lessonTime = data.get(i)[j];

                }
                if (j == 0 && !data.get(i)[j].equals("FREE")) {
                    lessonDay = data.get(i)[j];
                }
                if ((data.get(i)[j] != "FREE" && data.get(i)[j] != null) && j > 2) {
                    //System.out.println(i + " - " + j + ") " + data.get(i)[j]);
                    lessonName = data.get(i)[j];
                    lessonRoom = getClassRoom(lessonName);
                    lessonInstructor = getLessonInstructor(lessonName);

                    if (j >= 10) {
                        lessonCourse = "2";
                    }
                    else {
                        lessonCourse = "1";
                    }

                    Lesson lesson = new Lesson(lessonName, lessonTime, lessonInstructor, lessonRoom, lessonCourse, lessonDay);
                    if (i < 6) {
                        mondaySchedule.add(lesson);
                    }
                    else if (i > 6 && i < 12) {
                        tuesdaySchedule.add(lesson);
                    }
                    else if (i > 12 && i < 18) {
                        wednesdaySchedule.add(lesson);
                    }
                    else if (i > 18 && i < 24) {
                        thursdaySchedule.add(lesson);
                    }
                    else if (i > 24 && i < 30) {
                        fridaySchedule.add(lesson);
                    }
                    else if (i > 30 && i < 36) {
                        saturdaySchedule.add(lesson);
                    }
                }
            }
        }

        LinkedHashMap<String, Day> schedule = new LinkedHashMap<>();
        schedule.put("monday", new Day(mondaySchedule));
        schedule.put("tuesday", new Day(tuesdaySchedule));
        schedule.put("wednesday", new Day(wednesdaySchedule));
        schedule.put("thursday", new Day(thursdaySchedule));
        schedule.put("friday", new Day(fridaySchedule));
        schedule.put("saturday", new Day(saturdaySchedule));

        return schedule;
    }

    private String getClassRoom(String data) {
        if (data.substring(data.length() - 4).equals("Zoom")) {
            return "Zoom";
        } else if (!canBeParsedToInt(data.substring(data.length() - 3))) {
            return "Unknown";
        }
        else {
            return data.substring(data.length() - 3);
        }
    }

    private boolean canBeParsedToInt(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getLessonInstructor(String data) {
        String[] instructorNames = {"R. Smirnova", "lekt. A. Sisojevs", "prof. O.Kononova", "doc. I. Ščukins", "asoc.prof.S.Jaundalders", "lekt. S. Gorņiks", "doc. A. Paeglītis", "lekt. G. Spriņģis", "doc.L.Lavrinoviča", "lekt.Z.Lazda", "L.Zemīte", "J. Tretjakova", "M. Dobkeviča"};
        for (String name : instructorNames) {
            if (data.contains(name)){
                return name;
            }
        }
        return "";
    }
}