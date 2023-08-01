package com.dolphin.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class DatabaseBackupService {

    public void backupDatabase(String backupDirectory) throws IOException, InterruptedException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        String filename = String.format("%s_sensor_data.sql", timestamp);
        String fullPath = Paths.get(backupDirectory, filename).toString();

        String mysqldumpPath = "/opt/homebrew/bin/mysqldump"; // Replace with your actual mysqldump path

        // Build the command to execute mysqldump and redirect the output to the file
        ProcessBuilder processBuilder = new ProcessBuilder(mysqldumpPath, "-u", "root", "-pmanager", "sensor_data");
        processBuilder.redirectOutput(new File(fullPath));
        processBuilder.redirectErrorStream(true); // Redirect error stream to output stream

        log.info("Starting database backup...");
        log.info("Backup file will be saved at: {}", fullPath);

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            log.info("Database backup completed. Backup filename: {}", filename);

            // Change the working directory to the specified location
            String gitWorkingDirectory = "/Users/deepak.dhormare/Desktop/deepak_dhormare/dolphin-server/";
            ProcessBuilder gitProcessBuilder = new ProcessBuilder("git", "add", filename);
            gitProcessBuilder.directory(new File(gitWorkingDirectory));
            Process gitAddProcess = gitProcessBuilder.start();
            gitAddProcess.waitFor();

            ProcessBuilder gitPushProcessBuilder = new ProcessBuilder("git", "push");
            gitPushProcessBuilder.directory(new File(gitWorkingDirectory));
            Process gitPushProcess = gitPushProcessBuilder.start();
            gitPushProcess.waitFor();

            log.info("Backup file added to the Git repository and pushed successfully.");
        } else {
            log.error("Database backup failed.");
        }
    }


    public void restoreDatabase(String backupDirectory, String filename) throws IOException, InterruptedException {
        // Replace "your-username", "your-password", "your-database" with actual values

        // Specify the complete path of the backup file
        String fullPath = Paths.get(backupDirectory, filename).toString();

        String command = String.format("mysql -u root -pmanager sensor_data < %s", fullPath);

        log.info("Starting database restoration...");
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        log.info("Database restoration completed. Backup filename: {}", fullPath);
    }
}
