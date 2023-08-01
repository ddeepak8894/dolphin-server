package com.dolphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.services.DatabaseBackupService;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/database")@Slf4j
public class DatabaseController {

    private final DatabaseBackupService databaseBackupService;

    @Autowired
    public DatabaseController(DatabaseBackupService databaseBackupService) {
        this.databaseBackupService = databaseBackupService;
    }

    @GetMapping("/backup")
    public ResponseEntity<String> backupDatabase() {
        try {
            String backupDirectory = "/Users/deepak.dhormare/Desktop/deepak_dhormare/dolphin-server/backups"; // Specify your desired backup directory

            log.info("Starting database backup...");
            databaseBackupService.backupDatabase(backupDirectory);
            log.info("Database backup completed.");

            return ResponseEntity.ok("Database backup completed.");
        } catch (IOException | InterruptedException e) {
            log.error("Database backup failed.", e);
            return ResponseEntity.status(500).body("Database backup failed. Error: " + e.getMessage());
        }
    }

    @GetMapping("/restore")
    public ResponseEntity<String> restoreDatabase() {
        try {
            String filename = "20230801_1200_sensor_sensor_data.sql"; // Specify the filename of the backup
            String backupDirectory = "/Users/deepak.dhormare/Desktop/deepak_dhormare/dolphin-server/backups"; // Specify your desired backup directory

            databaseBackupService.restoreDatabase(backupDirectory, filename);
            return ResponseEntity.ok("Database restoration completed. Backup filename: " + filename);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(500).body("Database restoration failed. Error: " + e.getMessage());
        }
    }
}
