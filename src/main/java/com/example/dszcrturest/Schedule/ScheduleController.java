package com.example.dszcrturest.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
/*
    ScheduleController is a controller, which controlls Downloading files from provided link into specific folder
 */
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleDownloader scheduleDownloader;

    public void DownloadFile() throws MalformedURLException {
        String url = "https://dszc-daugavpils.rtu.lv/timetables/dszc.dienas.xlsx";
        String userHome = System.getProperty("user.dir") + "/src/main/java/com/example/dszcrturest/Schedule";
        String path = userHome + "/DownloadedSchedule/Schedule.xlsx";

        try {
            scheduleDownloader.downloadSchedule(url, path);
            System.out.println("File has been downloaded to: " + path);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}