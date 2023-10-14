package com.example.dszcrturest.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;


@RestController
@RequestMapping("/download")
public class ScheduleController {
    @Autowired
    private ScheduleDownloader scheduleDownloader;

    @GetMapping("/file")
    public void DownloadFile() throws MalformedURLException {
        String url =  "https://dszc-daugavpils.rtu.lv/timetables/dszc.dienas.xlsx";
        String path = "C:\\Users\\chern\\Documents\\dszcRTURest\\src\\main\\java\\com\\example\\dszcrturest\\Schedule\\DownloadedSchedule\\Schedule.xslx";

        try {
            scheduleDownloader.downloadSchedule(url, path);
            System.out.println("File has been downloaded");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}