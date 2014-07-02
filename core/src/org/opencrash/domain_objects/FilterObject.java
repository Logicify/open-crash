package org.opencrash.domain_objects;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Fong on 20.06.14.
 */
public class FilterObject {
    private boolean classFilter;
    private List<Integer> classesId;
    private boolean dateFilter;
    private HashMap<String,String> dateParameters;
    private boolean deviceFilter;
    private List<Integer> devicesId;
    private boolean applicationFilter;
    private List<Integer> applicationsId;
    private boolean grouping;
    private List<String> group_by;

    public boolean isClassFilter() {
        return classFilter;
    }

    public void setClassFilter(boolean classFilter) {
        this.classFilter = classFilter;
    }

    public List<Integer> getClassesId() {
        return classesId;
    }

    public void setClassesId(List<Integer> classesId) {
        this.classesId = classesId;
    }

    public boolean isDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(boolean dateFilter) {
        this.dateFilter = dateFilter;
    }

    public HashMap<String, String> getDateParameters() {
        return dateParameters;
    }

    public void setDateParameters(HashMap<String, String> dateParameters) {
        this.dateParameters = dateParameters;
    }

    public boolean isDeviceFilter() {
        return deviceFilter;
    }

    public void setDeviceFilter(boolean deviceFilter) {
        this.deviceFilter = deviceFilter;
    }

    public List<Integer> getDevicesId() {
        return devicesId;
    }

    public void setDevicesId(List<Integer> devicesId) {
        this.devicesId = devicesId;
    }

    public boolean isApplicationFilter() {
        return applicationFilter;
    }

    public void setApplicationFilter(boolean applicationFilter) {
        this.applicationFilter = applicationFilter;
    }

    public List<Integer> getApplicationsId() {
        return applicationsId;
    }

    public void setApplicationsId(List<Integer> applicationsId) {
        this.applicationsId = applicationsId;
    }

    public List<String> getGroup_by() {
        return group_by;
    }

    public void setGroup_by(List<String> group_by) {
        this.group_by = group_by;
    }

    public boolean isGrouping() {
        return grouping;
    }

    public void setGrouping(boolean grouping) {
        this.grouping = grouping;
    }
}


