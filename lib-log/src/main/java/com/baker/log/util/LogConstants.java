package com.baker.log.util;


public class LogConstants {
    private static boolean isDebug = true;
    private static String debugFilePath = "";

    private static boolean isPrintLineMessage = true;

    private static String businessType = "bbxnr-sdk-android";
    private static String appVersion = "1.1.0";
    private static String appName = "标贝虚拟人";

    public static String getAppVersion() {
        return appVersion;
    }

    public static void setAppVersion(String appVersion) {
        LogConstants.appVersion = appVersion;
    }

    public static String getAppName() {
        return appName;
    }

    public static void setAppName(String appName) {
        LogConstants.appName = appName;
    }

    public static String getBusinessType() {
        return businessType;
    }

    public static void setBusinessType(String businessType) {
        LogConstants.businessType = businessType;
    }

    public static boolean isIsPrintLineMessage() {
        return isPrintLineMessage;
    }

    public static void setPrintLineMessage(boolean isPrintLine) {
        isPrintLineMessage = isPrintLine;
    }

    public static boolean isIsDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean debug) {
        isDebug = debug;
    }

    public static String getDebugFilePath() {
        return debugFilePath;
    }

    public static void setDebugFilePath(String debugFilePath) {
        LogConstants.debugFilePath = debugFilePath;
    }
}
