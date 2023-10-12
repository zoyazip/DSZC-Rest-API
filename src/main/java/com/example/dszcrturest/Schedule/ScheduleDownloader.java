package com.example.dszcrturest.Schedule;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;

@Service
public class ScheduleDownloader {

    public void downloadSchedule(String fileUrl, String destinationPath) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                fileUrl,
                HttpMethod.GET,
                null,
                byte[].class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            byte[] bytes = responseEntity.getBody();

            try (OutputStream outputStream = new FileOutputStream(destinationPath)) {
                outputStream.write(bytes);
            }

            System.out.println("Excel file downloaded successfully.");
        } else {
            System.err.println("Failed to download the Excel file. Status code: " + responseEntity.getStatusCode());
        }
    }

}