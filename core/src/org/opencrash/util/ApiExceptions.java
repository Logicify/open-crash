package org.opencrash.util;

/**
 * Created by Fong on 28.05.14.
 */
public class ApiExceptions extends Exception {
    private Integer errorCode;

    public ApiExceptions(String message,Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
