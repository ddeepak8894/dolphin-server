package com.dolphin.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dolphin.services.DatabaseBackupService;

@Component
public class ScheduledBackupTask {

    @Autowired
    private DatabaseBackupService databaseBackupService;

    // Schedule daily backups at midnight (adjust cron expression as needed)
//    @Scheduled(cron = "0 0/12 * * * *")
//    public void scheduleBackup() {
//        try {
//            databaseBackupService.backupDatabase("/Users/deepak.dhormare/Desktop/deepak_dhormare/dolphin-server/backups");
//        } catch (Exception e) {
//            // Handle backup error
//        }
//    }
}