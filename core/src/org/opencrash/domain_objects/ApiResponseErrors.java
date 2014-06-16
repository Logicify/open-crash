package org.opencrash.domain_objects;

/**
 * Created by Fong on 28.05.14.
 */
public class ApiResponseErrors {
    private Integer code;
    private String message;

    public ApiResponseErrors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
