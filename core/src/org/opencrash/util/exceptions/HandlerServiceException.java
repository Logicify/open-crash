package org.opencrash.util.exceptions;

/**
 * Created by Fong on 29.05.14.
 */
public class HandlerServiceException extends Exception {
    private Integer errorCode;

    public HandlerServiceException(String message,Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
