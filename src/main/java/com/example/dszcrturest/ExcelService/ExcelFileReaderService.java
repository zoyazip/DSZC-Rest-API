package com.example.dszcrturest.ExcelService;

import com.example.dszcrturest.Model.Day;
import com.example.dszcrturest.Model.Lesson;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ExcelFileReaderService {
    private ArrayList<String[]> rawData = new ArrayList<>();
    private ArrayList<Day> data = new ArrayList<>();
    private String[] specialisations = {"Datorsistēmas, Informācijas tehnoloģija, Automātika un datortehnika", "Viedā elektroenerģētika", "Automobiļu transports", "Inženiertehnika, mehānika un mašīnbūve", "Mehatronika", "Būvniecība",};

    public ArrayList<ArrayList<HashMap<String, Day>>> readExcelFile(String filePath) throws IOException {

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
            this.rawData.add(rowData);
        }

        workbook.close();
        ArrayList<ArrayList<HashMap<String, Day>>> week = createContent(rawData);
        return week;
    }
    private ArrayList<ArrayList<HashMap<String, Day>>> createContent(List<String[]> data) {
        ArrayList<ArrayList<HashMap<String, Day>>> week = new ArrayList<>();

        List<String> days = new ArrayList<>();
        String lessonName = "";
        String lessonTime = "";
        String lessonInstructor = "";
        String lessonRoom = "";
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
                if ((data.get(i)[j] != "FREE" && data.get(i)[j] != null) && j > 2) {
                    System.out.println(i + " - " + j + ") " + data.get(i)[j]);
                    lessonName = data.get(i)[j];
                    lessonRoom = getClassRoom(lessonName);
                    lessonInstructor = getLessonInstructor(lessonName);
                    Lesson lesson = new Lesson(lessonName, lessonTime, lessonInstructor, lessonRoom);
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
                    else if (i > 30) {
                        saturdaySchedule.add(lesson);
                    }
                }

            }
        }

        HashMap<String, Day> monday = new HashMap<>();
        HashMap<String, Day> tuesday = new HashMap<>();
        HashMap<String, Day> wednesday = new HashMap<>();
        HashMap<String, Day> thursday = new HashMap<>();
        HashMap<String, Day> friday = new HashMap<>();
        HashMap<String, Day> saturady = new HashMap<>();

        monday.put("Monday", new Day(mondaySchedule));
        tuesday.put("Tuesday", new Day(tuesdaySchedule));
        wednesday.put("Wednesday", new Day(wednesdaySchedule));
        thursday.put("Thursday", new Day(thursdaySchedule));
        friday.put("Friday", new Day(fridaySchedule));
        saturady.put("Saturday", new Day(saturdaySchedule));

        ArrayList<HashMap<String, Day>> schedule = new ArrayList<>();

        schedule.add(monday);
        schedule.add(tuesday);
        schedule.add(wednesday);
        schedule.add(thursday);
        schedule.add(friday);
        schedule.add(saturady);

        week.add(schedule);
        return week;
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
        String[] instructorNames = {"R. Smirnova", "lekt. A. Sisojevs", "prof. O.Kononova", "doc. I. Ščukins", "asoc.prof.S.Jaundalders", "lekt. S. Gorņiks", "doc. A. Paeglītis", "lekt. G. Spriņģis", "doc.L.Lavrinoviča", "lekt.Z.Lazda"};
        for (String name : instructorNames) {
            if (data.contains(name)){
                return name;
            }
        }
        return "";
    }
}