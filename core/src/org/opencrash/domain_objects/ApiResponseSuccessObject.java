package org.opencrash.domain_objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 28.05.14.
 */
public class ApiResponseSuccessObject {

    private boolean success;
    private String timestamp;

    public boolean isSuccess() { return success; }
    public String getTimestamp() { return timestamp; }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}