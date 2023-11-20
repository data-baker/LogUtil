package com.baker.log.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

public class LogUtil {

    private static String tag = "TAG--->";

    public static void openLogUtilStream(Context context) {
        String debugPath = context.getFilesDir().getAbsolutePath() + File.separator + "log.txt";
        LogConstants.getInstance().setDebugFilePath(debugPath);
        WriteLog.openStream(context, debugPath);
    }

    public static void v(String message) {
        v((new Throwable()).getStackTrace(), tag, message);
    }

    public static void v(String tag, String message) {
        v((new Throwable()).getStackTrace(), tag, message);
    }

    public static void d(String message) {
        d((new Throwable()).getStackTrace(), tag, message);
    }

    public static void d(String tag, String message) {
        d((new Throwable()).getStackTrace(), tag, message);
    }


    public static void i(String message) {
        i((new Throwable()).getStackTrace(), tag, message);
    }

    public static void i(String tag, String message) {
        i((new Throwable()).getStackTrace(), tag, message);
    }

    public static void w(String message) {
        w((new Throwable()).getStackTrace(), tag, message);
    }

    public static void w(String tag, String message) {
        w((new Throwable()).getStackTrace(), tag, message);
    }

    public static void e(String message) {
        e((new Throwable()).getStackTrace(), tag, message);
    }

    public static void e(String tag, String message) {
        e((new Throwable()).getStackTrace(), tag, message);

    }

    public static void error(String message) {
        error((new Throwable()).getStackTrace(), tag, message);
    }

    public static void error(String tag, String message) {
        error((new Throwable()).getStackTrace(), tag, message);
    }


    private static void v(StackTraceElement[] sElements, String tag, String message) {
        String logMessage = getMethodNames(sElements, message);
        Log.v(tag, logMessage);
        WriteLog.v(tag, logMessage);
    }

    private static void d(StackTraceElement[] sElements, String tag, String message) {
        String logMessage = getMethodNames(sElements, message);
        Log.d(tag, logMessage);
        WriteLog.d(tag, logMessage);
    }

    private static void i(StackTraceElement[] sElements, String tag, String message) {
        String logMessage = getMethodNames(sElements, message);
        Log.i(tag, logMessage);
        WriteLog.i(tag, logMessage);
    }

    private static void w(StackTraceElement[] sElements, String tag, String message) {
        String logMessage = getMethodNames(sElements, message);
        Log.w(tag, logMessage);
        WriteLog.w(tag, logMessage);
    }

    private static void e(StackTraceElement[] sElements, String tag, String message) {
        String logMessage = getMethodNames(sElements, message);
        Log.e(tag, logMessage);
        WriteLog.w(tag, logMessage);
    }

    private static void error(StackTraceElement[] sElements, String tag, String message) {
        String logMessage = getMethodNames(sElements, message);
        Log.e(tag, logMessage);
        WriteLog.e(tag, logMessage);
    }

    private static final StringBuilder stringBuilder = new StringBuilder();

    private static String getMethodNames(StackTraceElement[] sElements, String message) {
        if (LogConstants.getInstance().isIsPrintLineMessage()) {
            stringBuilder.setLength(0);
            stringBuilder.append("(").append(TextUtils.isEmpty(sElements[1].getFileName()) ? "null" : sElements[1].getFileName())
                    .append("#").append(TextUtils.isEmpty(sElements[1].getMethodName()) ? "null" : sElements[1].getMethodName())
                    .append("()#").append(sElements[1].getLineNumber()).append(") ").append(message);
            return stringBuilder.toString();
        } else {
            return message;
        }
    }
}
