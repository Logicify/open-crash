package org.opencrash.domain_objects;

import org.json.simple.JSONObject;

/**
 * Created by Fong on 27.05.14.
 */
public class ParserObject {
    private JSONObject exception;
    private JSONObject applicationEnvironment;
    private JSONObject client;
    private String uid;
    private String exceptionClass;
    private String backtrace;
    private String message;
    private String wifiStatus;
    private String gpsStatus;
    private String screenOrientation;
    private Integer screenWidth;
    private Integer screenHeight;
    private String osVersion;
    private String device;

    public JSONObject getException() {
        return exception;
    }

    public void setException(JSONObject exception) {
        this.exception = exception;
    }

    public JSONObject getApplicationEnvironment() {
        return applicationEnvironment;
    }

    public void setApplicationEnvironment(JSONObject applicationEnvironment) {
        this.applicationEnvironment = applicationEnvironment;
    }

    public JSONObject getClient() {
        return client;
    }

    public void setClient(JSONObject client) {
        this.client = client;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getBacktrace() {
        return backtrace;
    }

    public void setBacktrace(String backtrace) {
        this.backtrace = backtrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScreenOrientation() {
        return screenOrientation;
    }

    public void setScreenOrientation(String screenOrientation) {
        this.screenOrientation = screenOrientation;
    }

    public String getWifiStatus() {
        return wifiStatus;
    }

    public void setWifiStatus(String wifiStatus) {
        this.wifiStatus = wifiStatus;
    }

    public String getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(String gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}


