package org.opencrash.domain_objects;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fong on 12.05.14.
 */
@Entity
@Table(name="obtained_exceptions")
public class ObtainedException extends IdentifiableEntity{
    private ExceptionClass exception_class;
    private String backtrace;
    private String message;
    private Application application;
    private Date create_at;
    private String wifi_on;
    private String gps_on;
    private String screen_orientation;
    private Integer screen_width;
    private Integer screen_height;
    private String Uid;
    private String OsVersion;
    private Device device;


    @ManyToOne
    @JoinColumn(name="exception_class_id")
    public ExceptionClass getException_class() {
        return exception_class;
    }

    public void setException_class(ExceptionClass exception_class) {
        this.exception_class = exception_class;
    }

    @Column(name="backtrace",columnDefinition = "text")
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

    @ManyToOne
    @JoinColumn(name="application_id")
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Column(name="Wifi_on")
    public String getWifi_on() {
        return wifi_on;
    }

    public void setWifi_on(String wifi_on) {
        this.wifi_on = wifi_on;
    }
    @Column(name="Gps_on")
    public String getGps_on() {
        return gps_on;
    }

    public void setGps_on(String gps_on) {
        this.gps_on = gps_on;
    }
    @Column(name = "screen_orientation")
    public String getScreen_orientation() {
        return screen_orientation;
    }

    public void setScreen_orientation(String screen_orientation) {
        this.screen_orientation = screen_orientation;
    }

    @Column(name = "screen_width")
    public Integer getScreen_width() {
        return screen_width;
    }

    public void setScreen_width(Integer screen_width) {
        this.screen_width = screen_width;
    }
    @Column(name = "screen_height")
    public Integer getScreen_height() {
        return screen_height;
    }

    public void setScreen_height(Integer screen_height) {
        this.screen_height = screen_height;
    }

    @Column(name = "uid")
    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
    @Column(name="os_version")
    public String getOsVersion() {
        return OsVersion;
    }

    public void setOsVersion(String osVersion) {
        OsVersion = osVersion;
    }
    @ManyToOne
    @JoinColumn(name="device_id")
    public Device getDevice() {
        return device;
    }
    public void setDevice(Device device) {
        this.device = device;
    }
}

