package com.baker.log.util;

import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LogBackupManager {

    private static final long MAX_LOG_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final long MAX_BACK_FILE_SIZE = 20 * 1024 * 1024; // 20MB
    private String logFilePath;
    private String backupFilePath;

    private File logFile;
    private File backupFile;

    public File getLogFile() {
        if (logFile == null) {
            logFile = new File(logFilePath);
        }
        return logFile;
    }

    public File getBackupFile() {
        if (backupFile == null) {
            backupFile = new File(backupFilePath);
        }
        return backupFile;
    }

    private LogBackupManager(String logFilePath, String backupFilePath) {
        this.logFilePath = logFilePath;
        this.backupFilePath = backupFilePath;
    }

    private static volatile LogBackupManager instance;

    public static LogBackupManager getInstance() {
        if (null == instance) {
            synchronized (LogBackupManager.class) {
                if (null == instance) {
                    instance = new LogBackupManager(LogConstants.getInstance().getDebugFilePath(), LogConstants.getInstance().getDebugFilePath().replace(".txt", "backup.log"));
                }
            }
        }
        return instance;
    }

    public void checkAndBackupLogFile() {
        long logFileSize = getLogFile().length();
        if (logFileSize > MAX_LOG_FILE_SIZE) {
            backupLogFile();
            clearLogFile();
        }
    }

    private void backupLogFile() {
        clearBackupLogFile();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.copy(getLogFile().toPath(), getBackupFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                fileInputStream = new FileInputStream(getLogFile());
                fileOutputStream = new FileOutputStream(getBackupFile());
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void clearBackupLogFile() {
        if (getBackupFile().length() > MAX_BACK_FILE_SIZE) {
            getBackupFile().delete();
        }
    }

    private void clearLogFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getLogFile());
            fileOutputStream.write(new byte[0]);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
