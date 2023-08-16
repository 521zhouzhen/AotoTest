package cn.com.pajk.common.report;

import java.util.Date;

public class ShareLog {
    public static String reportLog="";
    public static int stepNumber=1;
    public static String devicePlatformName="";
    public static String devicePlatformVer="";
    public static String deviceName="";
    public static String appVer="";
    public static String appPath="";
    public static String userName="";
    public static Date time=null;

    public static String getReportLog() {
        return reportLog;
    }

    public static void setReportLog(String reportLog) {
        ShareLog.reportLog = reportLog;
    }

    public static int getStepNumber() {
        return stepNumber;
    }

    public static void setStepNumber(int stepNumber) {
        ShareLog.stepNumber = stepNumber;
    }

    public static String getDevicePlatformName() {
        return devicePlatformName;
    }

    public static void setDevicePlatformName(String devicePlatformName) {
        ShareLog.devicePlatformName = devicePlatformName;
    }

    public static String getDevicePlatformVer() {
        return devicePlatformVer;
    }

    public static void setDevicePlatformVer(String devicePlatformVer) {
        ShareLog.devicePlatformVer = devicePlatformVer;
    }

    public static String getDeviceName() {
        return deviceName;
    }

    public static void setDeviceName(String deviceName) {
        ShareLog.deviceName = deviceName;
    }

    public static String getAppVer() {
        return appVer;
    }

    public static void setAppVer(String appVer) {
        ShareLog.appVer = appVer;
    }

    public static String getAppPath() {
        return appPath;
    }

    public static void setAppPath(String appPath) {
        ShareLog.appPath = appPath;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        ShareLog.userName = userName;
    }

    public static Date getTime() {
        return time;
    }

    public static void setTime(Date time) {
        ShareLog.time = time;
    }
}