package com.baker.log.util;

import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LogBackupManager {

    private static final long MAX_LOG_FILE_SIZE = 20* 1024 * 1024; // 20MB
    private String logFilePath;
    private String backupFilePath;

    public LogBackupManager(String logFilePath, String backupFilePath) {
        this.logFilePath = logFilePath;
        this.backupFilePath = backupFilePath;
    }
    public void checkAndBackupLogFile() {
        File logFile = new File(logFilePath);
        long logFileSize = logFile.length();
        if (logFileSize > MAX_LOG_FILE_SIZE) {
            backupLogFile();
            clearLogFile();
        }
    }
    private void backupLogFile() {
        File logFile = new File(logFilePath);
        File backupFile = new File(backupFilePath);
        if (backupFile.exists()) {
            backupFile.delete();
        }
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.copy(logFile.toPath(),backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }else {
                 fileInputStream = new FileInputStream(logFile);
                 fileOutputStream = new FileOutputStream(backupFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0,length);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void clearLogFile() {
        File logFile = new File(logFilePath);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(logFile);
            fileOutputStream.write(new byte[0]);
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
