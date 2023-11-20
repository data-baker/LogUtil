package com.baker.log.util;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WriteLog {
    // 控制当前日志打印的级别

    private static final int DB_LOG_VERBOSE = 2;
    private static final int DB_LOG_DEBUG = 3;
    private static final int DB_LOG_INFO = 4;
    private static final int DB_LOG_WARNING = 5;
    private static final int DB_LOG_ERROR = 6;

    private static FileOutputStream fos2;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static StringBuilder stringBuilder = new StringBuilder();


    public static void openStream(Context context, String filePath) {
        if (!LogConstants.getInstance().isDebug() || context == null) {
            return;
        }
        try {
            BakerLogUpload.getInstance().setContext(context);
            fos2 = new FileOutputStream(filePath, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //  日志的信息打印，支持不同的level的信息打印
    public static void v(String tag, String msg) {
        String logLevel = getMessageWithLevel(DB_LOG_VERBOSE);
        BakerLogUpload.getInstance().d(msg);
        writeLogs(logLevel + getTag(tag) + msg);
    }

    public static void d(String tag, String msg) {
        String logLevel = getMessageWithLevel(DB_LOG_DEBUG);
        BakerLogUpload.getInstance().d(msg);
        writeLogs(logLevel + getTag(tag) + msg);
    }

    public static void i(String tag, String msg) {
        String logLevel = getMessageWithLevel(DB_LOG_INFO);
        BakerLogUpload.getInstance().d(msg);
        writeLogs(logLevel + getTag(tag) + msg);
    }

    public static void w(String tag, String msg) {
        String logLevel = getMessageWithLevel(DB_LOG_WARNING);
        BakerLogUpload.getInstance().d(msg);
        writeLogs(logLevel + getTag(tag) + msg);
    }

    public static void e(String tag, String msg) {
        String logLevel = getMessageWithLevel(DB_LOG_ERROR);
        BakerLogUpload.getInstance().e(msg);
        writeLogs(logLevel + getTag(tag) + msg);
    }

    private static String getMessageWithLevel(int level) {
        switch (level) {
            case DB_LOG_DEBUG:
                return "DEBUG\t";
            case DB_LOG_INFO:
                return "INFO\t";
            case DB_LOG_WARNING:
                return "WARNING\t";
            case DB_LOG_ERROR:
                return "ERROR\t";
            default:
                return "Unknown\t";
        }
    }


    public static void writeLogs(String logString) {
        if (!LogConstants.getInstance().isDebug()) {
            return;
        }
        if (stringBuilder == null) return;
        if (fos2 == null) return;
        try {
            // 先检查备份,暂时不考虑手机容量不够的情况

            LogBackupManager.getInstance().checkAndBackupLogFile();

            stringBuilder.setLength(0);
            stringBuilder.append(simpleDateFormat.format(new Date(System.currentTimeMillis())));
            stringBuilder.append("\t\t");
            stringBuilder.append(logString);
            fos2.write(stringBuilder.toString().getBytes());
            fos2.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTag(String TAG) {
        StringBuilder TAGBuilder = new StringBuilder(TAG);
        while (TAGBuilder.length() < 16) {
            TAGBuilder.append(" ");
        }
        TAGBuilder.append("\t");
        TAG = TAGBuilder.toString();
        return TAG;
    }

    public static void closeStream() {
        try {
            if (!LogConstants.getInstance().isDebug() || fos2 == null) {
                return;
            }
            // 释放资源
            fos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
