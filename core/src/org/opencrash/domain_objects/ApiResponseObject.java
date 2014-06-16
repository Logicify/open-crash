package org.opencrash.domain_objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 28.05.14.
 */
public class ApiResponseObject {

    private boolean success;
    private Set<ApiResponseErrors> errors = new HashSet<ApiResponseErrors>();
    private String timestamp;

    public Set<ApiResponseErrors> getErrors() { return errors; }
    public boolean isSuccess() { return success; }
    public String getTimestamp() { return timestamp; }

    public void setErrors(Set<ApiResponseErrors> e) { errors = e; }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}