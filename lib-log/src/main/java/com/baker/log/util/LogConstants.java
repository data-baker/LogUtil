package com.baker.log.util;


public class LogConstants {
    private static volatile LogConstants instance;

    private LogConstants() {
    }

    public static LogConstants getInstance() {
        if (null == instance) {
            synchronized (LogConstants.class) {
                if (null == instance) {
                    instance = new LogConstants();
                }
            }
        }
        return instance;
    }

    private String logUrl;
    private String signatureString;

    private boolean isDebug = true;
    private String debugFilePath = "";
    private boolean isPrintLineMessage = true;
    private String businessType = "bbxnr-sdk-android";
    private String appVersion = "1.1.0";
    private String appName = "标贝虚拟人";
    private boolean isUpload = false;

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public String getLogUrl() {
        if (logUrl == null) {
            throw new NullPointerException("log :logUrl==null");
        }
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public String getSignatureString() {
        if (signatureString == null) {
            throw new NullPointerException("log :signatureString==null");
        }
        return signatureString;
    }

    public void setSignatureString(String signatureString) {
        this.signatureString = signatureString;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public boolean isIsPrintLineMessage() {
        return isPrintLineMessage;
    }

    public void setPrintLineMessage(boolean isPrintLine) {
        isPrintLineMessage = isPrintLine;
    }

    public boolean isIsDebug() {
        return isDebug;
    }

    public void setIsDebug(boolean debug) {
        isDebug = debug;
    }

    public String getDebugFilePath() {
        return debugFilePath;
    }

    public void setDebugFilePath(String debugFilePath) {
        this.debugFilePath = debugFilePath;
    }
}
