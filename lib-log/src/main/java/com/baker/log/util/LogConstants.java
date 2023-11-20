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
    private boolean isPrintLineMessage = true;
    private boolean isUpload = false;

    private boolean isDebug = true;
    private String debugFilePath = "";


    private String businessType = "bbxnr-sdk-android";
    private String appVersion = "1.1.0";
    private String appName = "标贝虚拟人";


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

    public void setLogConfig(String urlString, String signatureString, boolean isUpload) {
        this.logUrl = urlString;
        this.signatureString = signatureString;
        this.isUpload = isUpload;
    }

    public void setLogConfig(String urlString, String signatureString, boolean isUpload, boolean isPrintLineMessage) {
        this.logUrl = urlString;
        this.signatureString = signatureString;
        this.isUpload = isUpload;
        this.isPrintLineMessage = isPrintLineMessage;
    }

    public void setUploadConfig(String businessType, String appVersion, String appName) {
        this.businessType = businessType;
        this.appVersion = appVersion;
        this.appName = appName;
    }

    public void setSignatureString(String signatureString) {
        this.signatureString = signatureString;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public String getBusinessType() {
        return businessType;
    }


    public boolean isIsPrintLineMessage() {
        return isPrintLineMessage;
    }


    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
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
